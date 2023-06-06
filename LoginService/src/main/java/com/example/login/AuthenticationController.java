package com.example.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ResponseEntity<String> getUserRole(@RequestHeader("tokenId") String tokenId) throws AuthenticationException {
        String role = authenticationService.getUserRole(tokenId).toString();
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    @GetMapping("/rolecheck")
    public ResponseEntity<com.example.login.UserAccount> getUserBasedOnRole(@RequestParam("tokenId") String tokenId) throws AuthenticationException{
        com.example.login.UserAccount user = authenticationService.getUserIfRole(tokenId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

