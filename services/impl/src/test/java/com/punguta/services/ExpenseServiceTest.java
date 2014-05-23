package com.punguta.services;

import static com.punguta.services.ServicesFixture.generateUser;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Date;

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
import com.punguta.services.events.expense.SplitDetail;
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
        expenseDetail.setUserId(book.getUser().getId());
        for (Asset asset : book.getAssets()) {
            expenseDetail.setAssetAccountId(asset.getId());
            break;
        }
        expenseDetail.setNote("firstExpense");
        expenseDetail.setPosted(new Date());
        final SplitDetail splitDetail = new SplitDetail();
        splitDetail.setNote("Split note");
        splitDetail.setValue(10);
        splitDetail.setCategoryName("Alimente");
        expenseDetail.addSplitDetail(splitDetail);
        final ExpenseCreateEvent expenseCreateEvent = new ExpenseCreateEvent(expenseDetail);
        final ExpenseCreatedEvent expenseCreatedEvent = expenseService.create(expenseCreateEvent);

        assertNotNull(expenseCreatedEvent);
        assertNotNull(expenseCreatedEvent.getId());
        assertNotNull(expenseCreatedEvent.getDetail());
    }
}
