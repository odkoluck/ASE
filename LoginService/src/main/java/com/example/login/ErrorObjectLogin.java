package com.example.login;

public class ErrorObjectLogin {
    private String errorCode;

    public ErrorObjectLogin(String errorCode) {
        this.errorCode = errorCode;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}