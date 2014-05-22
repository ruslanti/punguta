package com.punguta.services;

import static com.punguta.services.ServicesFixture.generateUser;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.punguta.jpa.domains.Asset;
import com.punguta.jpa.domains.Book;
import com.punguta.jpa.domains.Currency;
import com.punguta.jpa.domains.User;
import com.punguta.jpa.repositories.CommodityRepository;
import com.punguta.jpa.repositories.UserRepository;
import com.punguta.services.config.ServicesConfiguration;
import com.punguta.services.events.expense.ExpenseCreateEvent;
import com.punguta.services.events.expense.ExpenseCreatedEvent;
import com.punguta.services.events.expense.ExpenseDetail;
import com.punguta.services.exceptions.PungutaException;

/**
 * User: ruslan
 * Date: 5/22/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServicesConfiguration.class})
public class ExpenseServiceTest {

    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExpenseService expenseService;

    @Autowired
    BookService bookService;

    private Book book;

    @Before
    public void before() throws PungutaException, IOException {
        User user = generateUser();
        userRepository.save(user);
        book = bookService.register(user, (Currency) commodityRepository.findByCode("USD"));
    }

    @Test
    public void firstExpense() {
        final ExpenseDetail expenseDetail = new ExpenseDetail();
        for (Asset asset : book.getAssets()) {
            expenseDetail.setAssetAccountId(asset.getId());
            break;
        }
        final ExpenseCreateEvent expenseCreateEvent = new ExpenseCreateEvent(expenseDetail);
        final ExpenseCreatedEvent expenseCreatedEvent = expenseService.create(expenseCreateEvent);
    }
}
