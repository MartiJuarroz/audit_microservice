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
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DirectConsumer {

    @Autowired
    EnvironmentVars environmentVars;

    @Autowired
    AuditLogger auditLogger;

    @Autowired
    ValidatorService validatorService;

    private String exchange;
    private String queue;
    private final Map<String, EventProcessor> listeners = new HashMap<>();

    public DirectConsumer init(String exchange, String queue) {
        this.exchange = exchange;
        this.queue = queue;
        return this;
    }

    public DirectConsumer addProcessor(String event, EventProcessor listener) {
        listeners.put(event, listener);
        return this;
    }
    public void startDelayed() {
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                start();
            }
        }, 10 * 1000);
    }

    public void start() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(environmentVars.envData.rabbitServerUrl);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(exchange, "direct");
            channel.queueDeclare(queue, false, false, false, null);

            channel.queueBind(queue, exchange, queue);

            new Thread(() -> {
                try {
                    auditLogger.info("RabbitMQ Escuchando " + queue);

                    channel.basicConsume(queue, true, new EventConsumer(channel));
                } catch (Exception e) {
                    auditLogger.error("RabbitMQ ", e);
                    startDelayed();
                }
            }).start();
        } catch (Exception e) {
            auditLogger.error("RabbitMQ ", e);
            startDelayed();
        }
    }

    class EventConsumer extends DefaultConsumer {
        EventConsumer(Channel channel) {
            super(channel);
        }

        @Override
        public void handleDelivery(String consumerTag, //
                                   Envelope envelope, //
                                   AMQP.BasicProperties properties, //
                                   byte[] body) {
            try {
                RabbitEvent event = RabbitEvent.fromJson(new String(body));

                EventProcessor l = listeners.get(event.type);
                if (l != null) {
                    auditLogger.info("RabbitMQ Consume article-data : " + event.type);

                    l.process(event);
                }
            } catch (Exception e) {
                auditLogger.error("RabbitMQ ", e);
            }
        }
    }
}
