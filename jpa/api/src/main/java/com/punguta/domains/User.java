package com.punguta.domains;

import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by ruslanti on 17.05.2014.
 */
@Entity
public class User extends AbstractEntity{

    private String email;

    private String password;

    private Locale locale;

    @ManyToOne
    private Book book;

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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
