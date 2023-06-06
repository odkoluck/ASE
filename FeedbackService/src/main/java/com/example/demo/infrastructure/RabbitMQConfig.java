package com.example.demo.infrastructure;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String HOST = "rabbitmq";
    public static final int PORT = 5672; // default RabbitMQ port
    public static final String EXCHANGE = "FB_Exchange";
    public static final String ROUTING_KEYS = "FB_Routing_Key";
    public static final String Feedback_QUEUE_Create = "FB_Queue";
    public static final String Feedback_QUEUE_Delete = "FB_Queue_Delete";
    public static final String Feedback_QUEUE_Update = "FB_Queue_Update";
    public static final String EXCHANGE_LOGIN = "Login_FB_Exchange";
    public static final String EXCHANGE_Event = "Event_FB_Exchange";
    public static final String ROUTING_KEYS_LOGIN = "FB_Login_Routing_Key";
    public static final String QUEUE_NAME_LOGIN = "FB_Login_Queue";
    public static final String QUEUE_NAME_EVENT = "FB_Event_Queue";


    //Exchanges
    @Bean
    public static TopicExchange exchange_login() {
        return new TopicExchange(EXCHANGE_LOGIN);
    }

    @Bean
    public static TopicExchange exchange_event() {

        return new TopicExchange(EXCHANGE_Event);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {return new RabbitAdmin(connectionFactory);}

    @Bean
    public static TopicExchange exchange() {

        return new TopicExchange(EXCHANGE);
    }

    //Bindings
    @Bean
    public static Binding bindingFBLoginQueue(TopicExchange exchange_login) {

        return BindingBuilder
                .bind(feedback_login_Queue())
                .to(exchange_login)
                .with(ROUTING_KEYS_LOGIN);

    }

    @Bean
    public static Binding bindingFBEventQueue(TopicExchange exchange_event) {
        return BindingBuilder
                .bind(feedback_event_Queue())
                .to(exchange_event)
                .with("feedback-event");
    }

    @Bean
    public static Binding bindingFBQueue(TopicExchange exchange) {

        return BindingBuilder
                .bind(feedbackQueue())
                .to(exchange)
                .with(ROUTING_KEYS);

    }

    @Bean
    public static Binding deleteFeedbackBinding(TopicExchange exchange) {
        return BindingBuilder
                .bind(feedbackDeleteQueue())
                .to(exchange)
                .with("feedback.delete");
    }

    @Bean
    public static Binding updateFeedback(TopicExchange exchange) {

        return BindingBuilder
                .bind(feedbackUpdateQueue())
                .to(exchange)
                .with("feedback.update");
    }

    //Queues
    @Bean
    public static Queue feedbackQueue() {
        return new Queue(Feedback_QUEUE_Create, false);
    }

    @Bean
    public static Queue feedback_login_Queue() {
        return new Queue(QUEUE_NAME_LOGIN, false);
    }
    @Bean
    public static Queue feedback_event_Queue() { return new Queue(QUEUE_NAME_EVENT, false);}

    @Bean
    public static Queue feedbackDeleteQueue() {
        return new Queue(Feedback_QUEUE_Delete);
    }

    @Bean
    public static Queue feedbackUpdateQueue() {
        return new Queue(Feedback_QUEUE_Update);
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
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter()); // Set the message converter
        return rabbitTemplate;
    }
}
