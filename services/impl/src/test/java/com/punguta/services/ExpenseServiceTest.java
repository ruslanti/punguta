package com.punguta.services;

import static com.punguta.services.ServicesFixture.generateUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.punguta.services.events.details.ExpenseDetail;
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
import com.punguta.services.events.expense.ExpenseReadEvent;
import com.punguta.services.events.expense.ExpenseRequestReadEvent;
import com.punguta.services.events.details.SplitDetail;
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
        final SplitDetail withdrawal = new SplitDetail();
        withdrawal.setAccountId(assetId);
        expenseDetail.setWithdrawalDetail(withdrawal);

        // expense splits: value, category and note
        Set<SplitDetail> depositSplits = new HashSet<>(1);
        final SplitDetail deposit = new SplitDetail();
        deposit.setNote("Split note");
        deposit.setValue(10);
        deposit.setCategoryName("Alimente");
        depositSplits.add(deposit);
        expenseDetail.setDepositDetails(depositSplits);

        final ExpenseCreatedEvent expenseCreatedEvent = expenseService.create(new ExpenseCreateEvent(expenseDetail));

        assertNotNull(expenseCreatedEvent);
        assertNotNull(expenseCreatedEvent.getId());
        assertNotNull(expenseCreatedEvent.getDetail());

        ExpenseRequestReadEvent expenseRequestReadEvent = new ExpenseRequestReadEvent();

        final ExpenseReadEvent list = expenseService.list(expenseRequestReadEvent);

        assertNotNull(list);
        final List<ExpenseDetail> transactionDetails = list.getDetails();
        assertEquals(1, transactionDetails.size());

        final ExpenseDetail responseDetail = transactionDetails.get(0);

        assertEquals(posted, responseDetail.getPosted());
        assertEquals("firstExpense", responseDetail.getNote());

        final SplitDetail withdrawalDetail = responseDetail.getWithdrawalDetail();
        assertNotNull(withdrawalDetail);
        assertEquals(10, withdrawalDetail.getValue());
        assertNotNull(withdrawalDetail.getAccountId());
        assertEquals(assetId, withdrawalDetail.getAccountId());

        final Set<SplitDetail> depositDetails = responseDetail.getDepositDetails();
        assertEquals(1, depositDetails.size());
        for (SplitDetail split : depositDetails) {
            assertEquals(10, split.getValue());
            assertEquals("Alimente", split.getCategoryName());
            assertEquals("Split note", split.getNote());
        }
    }
}
