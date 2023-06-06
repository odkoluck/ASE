package com.example.login.Listener;

import com.example.login.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.UUID;
@Configuration
public class EventListenerEmailAddress {

    private final UserAccountRepository userAccountRepository;

    public EventListenerEmailAddress(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @RabbitListener(queues = "emailQueue")
    public String processEvent(@Payload String tokenId) {
        try {
            String emailAddress = userAccountRepository.findByTokenId(tokenId).get().getEmailAddress();
            System.out.println(emailAddress);
            return emailAddress;
        } catch (NoSuchElementException e) {
            System.out.println("NO USER WITH THAT TOKENID FOUND");
            return null;
        }
    }
}
