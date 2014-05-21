package com.punguta.services;

import com.punguta.jpa.domains.Account;
import com.punguta.jpa.domains.Book;
import com.punguta.jpa.domains.Currency;
import com.punguta.jpa.domains.User;
import com.punguta.services.exceptions.PungutaException;

/**
 * User: ruslan
 * Date: 5/15/14
 */
public interface BookService {

    public void register(User user, Currency defaultCurrency) throws PungutaException;

    public void addAsset(Book book, String code, String name, String description);
    public void addAsset(Book book, String code, String name, String description, String currency);

    public void updateAccount(Account account);
}
