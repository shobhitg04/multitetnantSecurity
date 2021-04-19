package com.example.armour.code.interview.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
* Class created to simplify the demo application.
 * I am not encrypting password here.
* */


public class CustomEncoder extends BCryptPasswordEncoder {
    public CustomEncoder() {
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }
}
