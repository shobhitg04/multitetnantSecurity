package com.example.armour.code.interview.dto;

import java.io.Serializable;

/**

 */
public class AuthResponse implements Serializable {

    private String userName;
    private String token;

    public AuthResponse(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public AuthResponse setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AuthResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
