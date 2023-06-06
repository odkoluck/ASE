package com.example.login;

import com.example.login.UserAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserAccountRepository userAccountRepository;
    public UserRole getUserRole(String tokenId) throws AuthenticationException {
        Optional<UserAccount> userAcc = userAccountRepository.findByTokenId(tokenId);
        if(userAcc.isPresent()){
            return userAcc.get().getUserRole();
        }
        throw new AuthenticationException("User not Found!");
    }

    public UserAccount getUserIfRole(String tokenId) throws AuthenticationException{
        Optional<UserAccount> user = userAccountRepository.findByTokenId(tokenId);
        user.get().getUserRole();
        user.get().getToken().getValidUntil();
        if(user.get().getUserRole() == UserRole.ATTENDEE){
            if(user.get().getToken().getValidUntil().isAfter(LocalDateTime.now())){
                return user.get();
            }
        }
        throw new AuthenticationException("NO AUTHORIZATION");
    }

}
