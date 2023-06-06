package com.taggingService;

import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaggingRabbitMQConfig {
	
    public static final String HOST = "rabbitmq";
    public static final int PORT = 5672; // default RabbitMQ port
    public static final String EXCHANGE = "tagging_Exchange";
    public static final String QUEUE = "tagging_Queue";
    public static final String QUEUE_NAME = "tagging_Queue";
    public static final String ROUTING_KEY = "tagging.event";
   
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }
    
    @Bean
    public static Queue taggingQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public static TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public static Binding binding(Queue taggingQueue, TopicExchange exchange) {
        return BindingBuilder.bind(taggingQueue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
