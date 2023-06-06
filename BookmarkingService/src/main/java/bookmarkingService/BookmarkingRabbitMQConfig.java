package bookmarkingService;

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
public class BookmarkingRabbitMQConfig {
	
    public static final String HOST = "rabbitmq";
    public static final int PORT = 5672; // default RabbitMQ port
    public static final String EXCHANGE = "Recommender_Exchange";
    public static final String QUEUE = "Bookmarking_Queue";
    public static final String QUEUE_NAME = "Bookmarking_Queue";
    public static final String ROUTING_KEY = "bookmarking.event";
   
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }
    
    @Bean
    public static Queue bookmarkingQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public static TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public static Binding binding(Queue bookmarkingQueue, TopicExchange exchange) {
        return BindingBuilder.bind(bookmarkingQueue).to(exchange).with(ROUTING_KEY);
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
