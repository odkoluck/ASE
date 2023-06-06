package com.example.login;

import java.time.LocalDateTime;
import java.util.UUID;

public class Token {

    private String tokenId;
    private LocalDateTime validUntil;

    public Token(String tokenId, LocalDateTime validUntil){
        this.tokenId = tokenId;
        this.validUntil = validUntil;
    }
    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }


}

