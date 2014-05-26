package com.punguta.services;

import static com.punguta.services.ServicesFixture.generateUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import com.punguta.services.events.expense.ExpenseReadEvent;
import com.punguta.services.events.expense.ExpenseRequestReadEvent;
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
        User.setCurrent(user);
        book = bookService.register(user, (Currency) commodityRepository.findByCode("USD"));
    }

    @Test
    public void firstExpense() {
        final ExpenseDetail expenseDetail = new ExpenseDetail();
        Long assetId = null;
        for (Asset asset : book.getAssets()) {
            assetId = asset.getId();
            break;
        }
        // transaction
        expenseDetail.setNote("firstExpense");
        final Date posted = new Date();
        expenseDetail.setPosted(posted);

        // asset split (only account id)
        final SplitDetail assetSplit = new SplitDetail();
        assetSplit.setAccountId(assetId);
        expenseDetail.setAssetSplit(assetSplit);

        // expense splits: value, category and note
        final SplitDetail expenseSplit = new SplitDetail();
        expenseSplit.setNote("Split note");
        expenseSplit.setValue(10);
        expenseSplit.setCategoryName("Alimente");
        expenseDetail.addSplitDetail(expenseSplit);

        final ExpenseCreatedEvent expenseCreatedEvent = expenseService.create(new ExpenseCreateEvent(expenseDetail));

        assertNotNull(expenseCreatedEvent);
        assertNotNull(expenseCreatedEvent.getId());
        assertNotNull(expenseCreatedEvent.getDetail());

        ExpenseRequestReadEvent expenseRequestReadEvent = new ExpenseRequestReadEvent();

        final ExpenseReadEvent list = expenseService.list(expenseRequestReadEvent);

        assertNotNull(list);
        final List<ExpenseDetail> expenseDetails = list.getDetails();
        assertEquals(1, expenseDetails.size());

        final ExpenseDetail responseDetail = expenseDetails.get(0);

        assertEquals(posted, responseDetail.getPosted());
        assertEquals("firstExpense", responseDetail.getNote());

        final SplitDetail assetSplit1 = responseDetail.getAssetSplit();
        assertNotNull(assetSplit1);
        assertEquals(Integer.valueOf(10), Integer.valueOf(assetSplit1.getValue()));
        assertNotNull(assetSplit1.getAccountId());
        assertEquals(assetId, assetSplit1.getAccountId());

        final Set<SplitDetail> expenseSplits = responseDetail.getExpenseSplits();
        assertEquals(1, expenseSplits.size());
        for (SplitDetail split : expenseSplits) {
            assertEquals(Integer.valueOf(10), split.getValue());
            assertEquals("Alimente", split.getCategoryName());
            assertEquals("Split note", split.getNote());
        }
    }
}
