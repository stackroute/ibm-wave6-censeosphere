package com.stackroute.userloginservice.jwt;

import com.stackroute.userloginservice.domain.User;
import java.util.Map;

@FunctionalInterface
public interface TokenGenerator {
        Map<String, String> generateToken(User user);
    }

