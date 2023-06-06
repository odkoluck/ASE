package com.example.demo.infrastructure;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

public class RabbitMQFactory {
    private static final String HOST = "rabbitmq";
    private static final int PORT = 5672;
    private static final String VIRTUAL_HOST = "/";
    private static final String EXCHANGE = "FB_Exchange";
    private static final String EXCHANGE_LOGIN = "Login_FB_Exchange";

    private static final String EXCHANGE_EVENT = "Event_FB_Exchange";

    public static void main(String[] args) {
        CachingConnectionFactory factory = new CachingConnectionFactory(HOST, PORT);
        factory.setVirtualHost(VIRTUAL_HOST);

        try {
            RabbitAdmin rabbitAdmin = new RabbitAdmin(factory);
            rabbitAdmin.declareExchange(new TopicExchange(EXCHANGE));
            rabbitAdmin.declareExchange(new TopicExchange(EXCHANGE_LOGIN));
            rabbitAdmin.declareExchange(new TopicExchange(EXCHANGE_EVENT));

            factory.destroy(); // Clean up resources

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
