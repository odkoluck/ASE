package com.example.login;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserAccount {
    @Id
    private String id;
    @Indexed(unique = true)
    private String emailAddress;
    private String name;
    private String password;
    private com.example.login.Token token;
    private UserRole userRole;

    public UserAccount(String id, String emailAddress, String name, String password, UserRole userRole){
        this.id = id;
        this.emailAddress = emailAddress;
        this.name = name;
        this.password = password;
        this.userRole = userRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole (UserRole userRole) {
        this.userRole = userRole;
    }
}
