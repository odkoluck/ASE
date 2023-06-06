package com.example.login.Listener;

import com.example.login.LoginService;
import com.example.login.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.NoSuchElementException;
import java.util.UUID;
@Configuration
@AllArgsConstructor
public class EventListenerRole {

    private final RabbitTemplate rabbitTemplate;
    private final LoginService loginService;
    private final UserAccountRepository userAccountRepository;

    @RabbitListener(queues = "roleQueue")
    public String getRole(@Payload String tokenId) {
        try {
            String userRole = userAccountRepository.findByTokenId(tokenId).get().getUserRole().toString();
            System.out.println(userRole);
            return userRole;
        } catch (NoSuchElementException e) {
            System.out.println("NO USER WITH THAT TOKENID FOUND");
            return null;
        }
    }
}