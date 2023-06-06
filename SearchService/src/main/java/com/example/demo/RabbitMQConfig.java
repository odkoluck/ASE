package com.example.demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitAccessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String HOST = "rabbitmq";
    public static final int PORT = 5672; // default RabbitMQ port
    public static final String EXCHANGE = "Search_Exchange";
    public static final String ROUTING_KEYS = "Search_Routing_Key";
    public static final String QUEUE = "Search_Queue";
    public static final String QUEUE_NAME = "Search_Queue";



    @Bean
    public Queue feedbackQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(HOST, PORT);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange exchange() {

        return new TopicExchange(RabbitMQConfig.EXCHANGE);
    }

    @Bean
    public Binding bindingFBQueue(TopicExchange exchange) {

        return BindingBuilder
                .bind(feedbackQueue())
                .to(exchange)
                .with(ROUTING_KEYS);

    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter()); // Set the message converter
        return rabbitTemplate;
    }
}

