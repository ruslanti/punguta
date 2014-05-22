package com.punguta.services;

import java.util.Locale;
import java.util.UUID;

import com.punguta.jpa.domains.User;

/**
 * User: ruslan
 * Date: 5/22/14
 */
public class ServicesFixture {

    public static User generateUser() {
        User user = new User();
        user.setEmail("email@dot.com");
        user.setLocale(Locale.getDefault());
        user.setPassword(UUID.randomUUID().toString());
        return user;
    }

}
