package com.example.login.Listener;
import com.example.login.LoginService;
import com.example.login.UserAccountRepository;
import com.example.login.communicationConfig.MessagingConfig;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class FB_LoginListener {

    private final RabbitTemplate rabbitTemplate;
    private final LoginService loginService;
    private final UserAccountRepository userAccountRepository;

    @RabbitListener(queues = "FB_Login_Queue")
    public String processEvent(@Payload String tokenId) {
        try {

            String userID = userAccountRepository.findByTokenId(tokenId).get().getId();
            System.out.println(userID);
            return userID;
        } catch (NoSuchElementException e) {
            // Handle the exception here
            System.out.println("User not found");
            //String userId = "null";
            return null; // or any other appropriate action
        }
    }
}
