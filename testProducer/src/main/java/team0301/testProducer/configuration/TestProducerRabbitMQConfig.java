package team0301.testProducer.configuration;



import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestProducerRabbitMQConfig {

	public static final String HOST = "rabbitmq";
	public static final int PORT = 5672;

	public static final String EXCHANGE = "test_Producer_Exchange";
	public static final String ROUTING_KEYS = "test_Producer_Routing_Key";
	public static final String QUEUE = "test_Producer_Queue";
	
	@Bean
	public Queue testProducerQueue() {
		return new Queue(QUEUE);
	}

	
	@Bean
	public TopicExchange exchange() {
		
		return new TopicExchange(EXCHANGE);
	}
	
	@Bean
	public Binding bindingTestProducerQueue(Queue queue, TopicExchange exchange) {
		
		return BindingBuilder
				.bind(queue)
				.to(exchange)
				.with(ROUTING_KEYS);
	}

	@Bean
	public MessageConverter converter() {

		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory){
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(converter());
		return template;
	}



}
