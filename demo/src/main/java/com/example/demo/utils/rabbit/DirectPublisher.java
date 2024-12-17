package com.example.demo.utils.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.server.AuditLogger;
import com.example.demo.server.EnvironmentVars;
import com.example.demo.utils.gson.GsonTools;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service
public class DirectPublisher {

    @Autowired
    AuditLogger auditLogger;

    @Autowired
    EnvironmentVars environmentVars;

    public void publish(String exchange, String queue, RabbitEvent message) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(environmentVars.envData.rabbitServerUrl);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(exchange, "direct");
            channel.queueDeclare(queue, false, false, false, null);

            channel.queueBind(queue, exchange, queue);

            channel.basicPublish(exchange, queue, null, GsonTools.toJson(message).getBytes());

            auditLogger.info("RabbitMQ Emit " + message.type);
        } catch (Exception e) {
            auditLogger.error("RabbitMQ no se pudo encolar " + message.type, e);
        }
    }
}
