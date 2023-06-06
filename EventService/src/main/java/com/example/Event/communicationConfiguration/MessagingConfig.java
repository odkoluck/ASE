package com.example.Event.communicationConfiguration;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.data.mongodb.core.aggregation.SelectionOperators;

@Configuration
public class MessagingConfig {

    public static final String HOST = "rabbitmq";
    public static final int PORT = 5672;
    public static final String QUEUE = "eventQueue";
    public static final String ROLE_QUEUE = "roleQueue";
    public static final String SEARCH_EVENT_QUEUE = "searchEventQueue";
    public static final String DELETE_EVENT_QUEUE = "deleteEventQueue";
    public static final String UPDATE_EVENT_QUEUE = "updateEventQueue";
    public static final String EMAIL_QUEUE = "emailQueue";
    public static final String EXCHANGE = "eventExchange";
    public static final String ROLE_EXCHANGE = "roleExchange";
    public static final String SEARCH_EVENT_EXCHANGE = "searchEventExchange";
    public static final String DELETE_EVENT_EXCHANGE = "deleteEventExchange";
    public static final String UPDATE_EVENT_EXCHANGE = "updateEventExchange";
    public static final String EMAIL_EXCHANGE = "emailExchange";
    public static final String ROLE_ROUTING_KEY = "roleRoutingKey";
    public static final String ROUTING_KEY = "eventRoutingKey";
    public static final String SEARCH_EVENT_ROUTING_KEY = "searchEventRoutingKey";
    public static final String DELETE_EVENT_ROUTING_KEY = "deleteEventRoutingKey";
    public static final String UPDATE_EVENT_ROUTING_KEY = "updateEventRoutingKey";
    public static final String EMAIL_ROUTING_KEY = "emailRoutingKey";
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }
    @Bean
    public static Queue eventQueue() {
        return new Queue(QUEUE);
    }
    @Bean
    public static Queue roleQueue(){
        return new Queue(ROLE_QUEUE);
    }

    @Bean
    public static Queue searchEventQueue(){return new Queue(SEARCH_EVENT_QUEUE);}
    @Bean
    public static Queue deleteEventQueue(){return new Queue(DELETE_EVENT_QUEUE);}
    @Bean
    public static Queue updateEventQueue(){return new Queue(UPDATE_EVENT_QUEUE);}
    @Bean
    public static Queue emailQueue(){return new Queue(EMAIL_QUEUE);}
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(HOST, PORT);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }
    @Bean
    public static TopicExchange eventExchange(){
        return new TopicExchange(EXCHANGE);
    }
    @Bean
    public static TopicExchange roleExchange(){
        return new TopicExchange(ROLE_EXCHANGE);
    }
    @Bean
    public static TopicExchange searchEventExchange(){
       return new TopicExchange(SEARCH_EVENT_EXCHANGE);
    }
    @Bean
    public static TopicExchange deleteEventExchange(){return new TopicExchange(DELETE_EVENT_EXCHANGE);}
    @Bean
    public static TopicExchange updateEventExchange(){return new TopicExchange(UPDATE_EVENT_EXCHANGE);}
    @Bean
    public static TopicExchange emailExchange(){return new TopicExchange(EMAIL_EXCHANGE);}

    @Bean
    public static Binding binding(Queue eventQueue, TopicExchange eventExchange){
        return BindingBuilder.bind(eventQueue).to(eventExchange).with(ROUTING_KEY);
    }
    @Bean
    public Binding roleBinding(Queue roleQueue, TopicExchange roleExchange){
        return BindingBuilder.bind(roleQueue).to(roleExchange).with(ROLE_ROUTING_KEY);
    }
    @Bean
    public Binding serachEventBinding(Queue searchEventQueue, TopicExchange searchEventExchange){
        return BindingBuilder.bind(searchEventQueue).to(searchEventExchange).with(SEARCH_EVENT_ROUTING_KEY);
    }
    @Bean
    public Binding deleteEventBinding(Queue deleteEventQueue, TopicExchange deleteEventExchange){
        return BindingBuilder.bind(deleteEventQueue).to(deleteEventExchange).with(DELETE_EVENT_ROUTING_KEY);
    }
    @Bean
    public Binding updateEventBinding(Queue updateEventQueue, TopicExchange updateEventExchange){
        return BindingBuilder.bind(updateEventQueue).to(updateEventExchange).with(UPDATE_EVENT_ROUTING_KEY);
    }
    @Bean Binding emailBinding(Queue emailQueue, TopicExchange emailExchange){
        return BindingBuilder.bind(emailQueue).to(emailExchange).with(EMAIL_ROUTING_KEY);
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
