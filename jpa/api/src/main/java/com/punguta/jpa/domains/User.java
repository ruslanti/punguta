package com.punguta.jpa.domains;

import java.util.Locale;

import javax.persistence.Entity;

/**
 * Created by ruslanti on 17.05.2014.
 */
@Entity
public class User extends AbstractEntity{

    private String email;

    private String password;

    private Locale locale;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
