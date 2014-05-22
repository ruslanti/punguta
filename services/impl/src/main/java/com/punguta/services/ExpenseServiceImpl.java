package com.punguta.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.punguta.jpa.domains.Account;
import com.punguta.jpa.domains.Book;
import com.punguta.jpa.domains.Expense;
import com.punguta.jpa.domains.Split;
import com.punguta.jpa.domains.Transaction;
import com.punguta.jpa.repositories.AccountRepository;
import com.punguta.jpa.repositories.BookRepository;
import com.punguta.jpa.repositories.SplitRepository;
import com.punguta.jpa.repositories.TransactionRepository;
import com.punguta.services.events.SplitDetail.SplitDetail;
import com.punguta.services.events.expense.ExpenseCreateEvent;
import com.punguta.services.events.expense.ExpenseCreatedEvent;
import com.punguta.services.events.expense.ExpenseDeleteEvent;
import com.punguta.services.events.expense.ExpenseDeletedEvent;
import com.punguta.services.events.expense.ExpenseDetail;
import com.punguta.services.events.expense.ExpenseReadEvent;
import com.punguta.services.events.expense.ExpenseRequestReadEvent;
import com.punguta.services.events.expense.ExpenseUpdateEvent;
import com.punguta.services.events.expense.ExpenseUpdatedEvent;

/**
 * Created by ruslanti on 21.05.2014.
 */
@Service
public class ExpenseServiceImpl implements ExpenseService{
    private static final Logger logger = LoggerFactory.getLogger(ExpenseServiceImpl.class);

    @Resource
    private BookRepository bookRepository;

    @Resource
    private AccountRepository accountRepository;

    @Resource
    private TransactionRepository transactionRepository;

    @Resource
    private SplitRepository splitRepository;

    @Override
    public ExpenseCreatedEvent create(ExpenseCreateEvent expenseCreateEvent) {
        final Transaction transaction = new Transaction();
        final ExpenseDetail expenseDetail = expenseCreateEvent.getDetail();
        
        transaction.setDate(expenseDetail.getPosted());
        transaction.setNote(expenseDetail.getNote());

        final Book book = bookRepository.findByUser(expenseDetail.getUserId());
        final Account assetAccount = accountRepository.findOne(expenseDetail.getAssetAccountId());
        final Expense expenseAccount = book.getExpense();
        final Set<Split> splits = new HashSet<Split>();
        int assetValue = 0;

        for (SplitDetail splitDetail : expenseDetail.getSplits()) {
            final Split expenseSplit = new Split();
            expenseSplit.setAccount(expenseAccount);
            final Integer value = splitDetail.getValue();
            expenseSplit.setQty(value);
            expenseSplit.setValue(value);
            expenseSplit.setNote(splitDetail.getNote());
            splits.add(expenseSplit);
            assetValue += value;
        }

        final Split assetSplit = new Split();
        assetSplit.setAccount(assetAccount);
        assetSplit.setQty(assetValue);
        assetSplit.setValue(assetValue);
        splits.add(assetSplit);

        transaction.setSplits(splits);
        
        splitRepository.save(splits);
        transactionRepository.save(transaction);

        return new ExpenseCreatedEvent(transaction.getId(), expenseDetail);
    }

    @Override
    public ExpenseUpdatedEvent update(ExpenseUpdateEvent expenseUpdateEvent) {
        return new ExpenseUpdatedEvent();
    }

    @Override
    public ExpenseDeletedEvent delete(ExpenseDeleteEvent expenseDeleteEvent) {
        return ExpenseDeletedEvent.notFound(expenseDeleteEvent.getId());
    }

    @Override
    public ExpenseReadEvent list(ExpenseRequestReadEvent expenseRequestReadEvent) {
        List<ExpenseDetail> expenseDetails = new ArrayList<ExpenseDetail>();
        expenseDetails.add(new ExpenseDetail());
        ExpenseReadEvent readEvent = new ExpenseReadEvent(Collections.emptyList());
        return readEvent;
    }
}
