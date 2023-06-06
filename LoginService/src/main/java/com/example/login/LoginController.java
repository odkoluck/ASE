package com.example.login;

//import org.springframework.data.repository.query.Param;
import com.example.login.LoginService;
import com.example.login.Token;
import com.example.login.UserAccount;
import com.example.login.communicationConfig.MessagingConfig;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.login.UserRole.ADMINISTRATOR;
import static com.example.login.UserRole.ORGANIZER;
import static com.example.login.UserRole.ATTENDEE;

@RestController
@RequestMapping("/api")
@CrossOrigin
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private RabbitTemplate template;
    private final UserAccountRepository userAccountRepository;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials) throws AuthenticationException {
        return loginService.login(credentials);
    }
    @PostMapping("/users/create/initial")
    public UserAccount createInitialUsers(@RequestBody UserAccount userAccount) throws AuthenticationException {
       return loginService.createUser(userAccount);
    }
    @PostMapping("/users/create")
    public UserAccount createUser(@RequestBody UserAccount userAccount, @RequestHeader String tokenId) throws AuthenticationException {
        if (userAccountRepository.findByTokenId(tokenId).get().getUserRole() == ADMINISTRATOR) {
            loginService.createUser(userAccount);
            template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, userAccount);
            return userAccount;
        } else {
            throw new AuthenticationException("NO PERMISSION TO CREATE USER");
        }
    }
    @GetMapping("/users")
    public List<UserAccount> getAllUser(@RequestHeader String tokenId) throws AuthenticationException {
        if (authenticationService.getUserRole(tokenId) == ADMINISTRATOR) {
            return loginService.getAllUsers();
        }
        throw new AuthenticationException("NO PERMISSION TO SEE ALL USERS");
    }
    @GetMapping("/users/my")
    public Optional<UserAccount> getMyUser(@RequestHeader String tokenId) throws AuthenticationException{
        return userAccountRepository.findByTokenId(tokenId);
    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUserAccount(@RequestHeader String tokenId, @PathVariable String userId) throws AuthenticationException {
        if (authenticationService.getUserRole(tokenId) == ADMINISTRATOR) {
            return loginService.deleteUser(userId);
        } else {
            throw new AuthenticationException("NO PERMISSION TO DELETE A USER");
        }
    }
    @PutMapping("/users/{userId}")
    public UserAccount updateUser(@RequestBody UserAccount userAccount, @PathVariable("userId") String userId, @RequestHeader String tokenId) throws AuthenticationException {
        String updater = userAccountRepository.findByTokenId(tokenId).get().getId();
            if (updater.equals(userId)) {
                return loginService.updateUser(userAccount);
            } else {
            throw new AuthenticationException("USERID DOES NOT MATCH, YOU CAN ONLY UPDATE YOUR OWN USERACCOUNT");
        }
        }
}
