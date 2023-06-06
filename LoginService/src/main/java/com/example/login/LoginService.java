package com.example.login;


import com.example.login.Token;
import com.example.login.UserAccount;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import com.example.demo.UserAccountRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LoginService {
    private final com.example.login.UserAccountRepository userAccountRepository;

    public Token login(Credentials credentials) throws AuthenticationException {
        Optional<UserAccount> userAccount = userAccountRepository.findByEmailAddressAndPassword(credentials.getEmailAddress(), credentials.getPassword());
        if (userAccount.isPresent()) {
            UserAccount toUpdate = userAccount.get();
            toUpdate.setToken(new Token(UUID.randomUUID().toString(), LocalDateTime.now().plusHours(8)));
            toUpdate = userAccountRepository.save(toUpdate);
            return toUpdate.getToken();

        }
        throw new AuthenticationException("Invalid credentials");
    }
    public Optional<UserAccount> getUserByTokenId(String tokenId) throws AuthenticationException {
        Optional<UserAccount> user = userAccountRepository.findByTokenId(tokenId);
        if (user.isPresent()) {
            return userAccountRepository.findByTokenId(tokenId);
        }
        throw new AuthenticationException("User not found");
    }

    public List<UserAccount> getAllUsers(){
        return userAccountRepository.findAll();
    }

    public UserAccount createUser(UserAccount userAccount) throws AuthenticationException{
        if(userAccountRepository.existsUserAccountByEmailAddress(userAccount.getEmailAddress())){
            throw new AuthenticationException("USER WITH THAT EMAIL ADDRESS ALREADY EXISTS");
        }
        if(userAccount.getEmailAddress().isEmpty() || userAccount.getName().isEmpty() || userAccount.getPassword().isEmpty() || userAccount.getUserRole() == null){
            throw new AuthenticationException("FAILED TO CREATE USER, PLEASE PROVIDE ALL DETAILS");
        } else {
            userAccountRepository.save(userAccount);
            return userAccount;
        }
    }
    public UserAccount updateUser(UserAccount userAccount) throws AuthenticationException {
        UserAccount savedEntity = userAccountRepository.findById(userAccount.getId()).orElseThrow(() ->
                new AuthenticationException("CAN´T FIND USER TO UPDATE"));
        if(!userAccount.getName().isEmpty()){ savedEntity.setName(userAccount.getName());}
        if(!userAccount.getEmailAddress().isEmpty()){savedEntity.setEmailAddress(userAccount.getEmailAddress());}
        if(!userAccount.getPassword().isEmpty()){ savedEntity.setPassword(userAccount.getPassword());}
        if (userAccountRepository.existsUserAccountByEmailAddress(userAccount.getEmailAddress())) {
            throw new AuthenticationException("USER WITH THAT EMAIL ADDRESS ALREADY EXISTS");
        }
        return userAccountRepository.save(savedEntity);
    }
    public ResponseEntity<?> deleteUser(String id) throws AuthenticationException{
        UserAccount userToDelete = userAccountRepository.findById(id).get();
        if(userAccountRepository.existsUserAccountByEmailAddress(userToDelete.getEmailAddress())){
            userAccountRepository.delete(userToDelete);
            return ResponseEntity.ok("User successfully deleted");
        } else {
            throw new AuthenticationException("USER TO DELETE DOES NOT EXIST");
        }
    }


}
