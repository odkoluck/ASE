package com.example.login.communicationConfig;

import org.apache.catalina.Host;
import org.springframework.amqp.core.*;
import org.springframework.boot.actuate.amqp.RabbitHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitAccessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.rabbitmq.client.AMQP.PROTOCOL.PORT;
import static org.springframework.amqp.rabbit.core.RabbitAdmin.QUEUE_NAME;
import static org.springframework.http.HttpHeaders.HOST;

@Configuration
public class MessagingConfig {

    public static final String HOST = "rabbitmq";
    public static final Integer PORT = 5672;
    public static final String QUEUE = "loginQueue";
    public static final String EXCHANGE = "loginExchange";
    public static final String ROUTING_KEY = "loginRoutingKey";
    @Bean
    public Queue loginQueue() {
        return new Queue (QUEUE);
    }
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(HOST, PORT);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }
    @Bean
    public TopicExchange loginExchange(){
        return new TopicExchange(EXCHANGE);
    }
    @Bean
    public Binding binding(Queue loginQueue, TopicExchange loginExchange){
        return BindingBuilder.bind(loginQueue).to(loginExchange).with(ROUTING_KEY);
    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
