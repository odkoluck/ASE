package com.example.login;

import com.example.login.UserAccount;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {
    Optional<UserAccount> findByEmailAddressAndPassword(String emailAddress, String password);
    @Query("{'token.tokenId':  ?0}")
    Optional<UserAccount> findByTokenId(String tokenId);
    Optional<UserAccount> findByEmailAddress(String emailAddress);
    Optional<UserAccount> findById(String id);

    boolean existsUserAccountByEmailAddress(String emailAddress);

}
