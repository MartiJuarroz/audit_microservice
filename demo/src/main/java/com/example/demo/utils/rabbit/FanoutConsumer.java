package com.example.demo.utils.rabbit;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.demo.server.AuditLogger;
import com.example.demo.server.EnvironmentVars;
import com.example.demo.server.ValidatorService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FanoutConsumer {

    @Autowired
    EnvironmentVars environmentVars;

    @Autowired
    ValidatorService validatorService;

    @Autowired
    AuditLogger auditLogger;

    private String exchange;
    private final Map<String, EventProcessor> listeners = new HashMap<>();

    public FanoutConsumer init(String exchange) {
        this.exchange = exchange;
        return this;
    }

    public FanoutConsumer addProcessor(String event, EventProcessor listener) {
        listeners.put(event, listener);
        return this;
    }

    public void startDelayed() {
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                start();
            }
        }, 10 * 1000); // En 10 segundos reintenta.
    }

    public void start() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(environmentVars.envData.rabbitServerUrl);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(exchange, "fanout");
            String queueName = channel.queueDeclare("", false, false, false, null).getQueue();

            channel.queueBind(queueName, exchange, "");

            new Thread(() -> {
                try {
                    auditLogger.info("RabbitMQ Fanout Conectado");

                    Consumer consumer = new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag, //
                                                   Envelope envelope, //
                                                   AMQP.BasicProperties properties, //
                                                   byte[] body) {
                            try {
                                RabbitEvent event = RabbitEvent.fromJson(new String(body));

                                EventProcessor eventConsumer = listeners.get(event.type);
                                if (eventConsumer != null) {
                                    auditLogger.info("RabbitMQ Consume " + event.type);

                                    eventConsumer.process(event);
                                }
                            } catch (Exception e) {
                                auditLogger.error("RabbitMQ Logout", e);
                            }
                        }
                    };
                    channel.basicConsume(queueName, true, consumer);
                } catch (Exception e) {
                    auditLogger.info("RabbitMQ ArticleValidation desconectado");
                    startDelayed();
                }
            }).start();
        } catch (Exception e) {
            auditLogger.error("RabbitMQ ArticleValidation desconectado", e);
            startDelayed();
        }
    }
}
