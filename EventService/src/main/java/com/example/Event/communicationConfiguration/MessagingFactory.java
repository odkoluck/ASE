package com.example.Event.communicationConfiguration;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

public class MessagingFactory {
    private static final String HOST = "rabbitmq";
    private static final int PORT = 5672;
    private static final String VIRTUAL_HOST = "/";
    private static final String EXCHANGE = "eventExchange";
    public static final String ROLE_EXCHANGE = "roleExchange";

    public static void main(String[] args) {
        CachingConnectionFactory factory = new CachingConnectionFactory(HOST, PORT);
        factory.setVirtualHost(VIRTUAL_HOST);

        try {
            RabbitAdmin rabbitAdmin = new RabbitAdmin(factory);
            rabbitAdmin.declareExchange(new TopicExchange(EXCHANGE));
            rabbitAdmin.declareExchange(new TopicExchange(ROLE_EXCHANGE));

            factory.destroy();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//SCHAUEN ob nötig!