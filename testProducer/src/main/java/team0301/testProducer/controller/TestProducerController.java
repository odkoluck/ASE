package team0301.testProducer.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import team0301.testProducer.configuration.TestProducerRabbitMQConfig;
import team0301.testProducer.model.TestProducerRequest;

@RestController
public class TestProducerController {
	
	@Autowired
	RabbitTemplate template;
	
	@RequestMapping(value="/EventInventoryService", method=RequestMethod.POST,
			produces="application/json", consumes="application/json")
	public String testProducerController(@RequestBody TestProducerRequest testProducerRequest) {
		
		try {
			template.convertAndSend(TestProducerRabbitMQConfig.EXCHANGE, TestProducerRabbitMQConfig.ROUTING_KEYS, testProducerRequest);
		}catch (Exception e) {
            System.out.println(e.getMessage());
            }
		return "send message";

	}
}
