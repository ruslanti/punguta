package com.punguta.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
import com.punguta.jpa.repositories.CategoryRepository;
import com.punguta.jpa.repositories.CommodityRepository;
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
    private CategoryRepository categoryRepository;

    @Autowired
    private AccountFactory accountFactory;

    @Override
    public Book register(User user, Currency defaultCurrency) throws PungutaException, IOException {
        Book book = new Book();
        book.setUser(user);
        book.setDefaultCommodity(defaultCurrency);

        final Asset asset = accountFactory.createAsset(defaultCurrency);
        accountRepository.save(asset);
        book.addAsset(asset);
        book.setExpense(accountFactory.createExpense(defaultCurrency));
        book.setIncome(accountFactory.createIncome(defaultCurrency));
        book.setLoan(accountFactory.createLoan(defaultCurrency));
        book.setLiability(accountFactory.createLiability(defaultCurrency));

        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("categories_ro.json");
        Category rootCategory = CategoryBuilder.loadFromJson(inputStream).build();
        List<Category> categories = new ArrayList<Category>();
        categoryRepository.save(rootCategory);
        book.setCategories(rootCategory.getChildren());

        bookRepository.save(book);
        return book;
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
