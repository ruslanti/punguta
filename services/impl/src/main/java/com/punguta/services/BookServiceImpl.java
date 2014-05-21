package com.punguta.services;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.punguta.jpa.domains.Account;
import com.punguta.jpa.domains.Asset;
import com.punguta.jpa.domains.Book;
import com.punguta.jpa.domains.Category;
import com.punguta.jpa.domains.Commodity;
import com.punguta.jpa.domains.Currency;
import com.punguta.jpa.domains.User;
import com.punguta.jpa.repositories.AccountRepository;
import com.punguta.jpa.repositories.BookRepository;
import com.punguta.jpa.repositories.CommodityRepository;
import com.punguta.services.exceptions.BookDefinedException;
import com.punguta.services.exceptions.PungutaException;
import com.punguta.services.helpers.AccountFactory;
import com.punguta.services.helpers.CategoryBuilder;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private AccountFactory accountFactory;

    @Override
    public void register(User user, Currency defaultCurrency) throws PungutaException {
        if (user.getBook() != null) {
            throw new BookDefinedException("User "+user+" already has registered book");
        }
        Book book = new Book();
        book.setDefaultCommodity(defaultCurrency);

        book.addAsset(accountFactory.createAsset(defaultCurrency));
        book.setExpense(accountFactory.createExpense(defaultCurrency));
        book.setIncome(accountFactory.createIncome(defaultCurrency));
        book.setLoan(accountFactory.createLoan(defaultCurrency));
        book.setLiability(accountFactory.createLiability(defaultCurrency));

        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("categories_ro.json");
        Category rootCategory = null;
        try {
            rootCategory = CategoryBuilder.loadFromJson(inputStream).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (rootCategory != null) {
            book.setCategories(rootCategory.getChildren());
        }

        bookRepository.save(book);
        user.setBook(book);
    }

    @Override
    public void addAsset(Book book, String code, String name, String description) {
        final Asset asset = accountFactory.createAsset(code, name, description, book.getDefaultCommodity());
        accountRepository.save(asset);
        book.addAsset(asset);
    }

    @Override
    public void addAsset(Book book, String code, String name, String description, String currencyCode) {
        final Commodity commodity = commodityRepository.findByCode(currencyCode);
        final Asset asset = accountFactory.createAsset(code, name, description, commodity);
        accountRepository.save(asset);
        book.addAsset(asset);
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }
}
