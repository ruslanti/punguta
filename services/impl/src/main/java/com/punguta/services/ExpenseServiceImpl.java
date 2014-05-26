package com.punguta.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.punguta.jpa.domains.Account;
import com.punguta.jpa.domains.Asset;
import com.punguta.jpa.domains.Book;
import com.punguta.jpa.domains.Category;
import com.punguta.jpa.domains.Expense;
import com.punguta.jpa.domains.Split;
import com.punguta.jpa.domains.Transaction;
import com.punguta.jpa.domains.User;
import com.punguta.jpa.repositories.AccountRepository;
import com.punguta.jpa.repositories.BookRepository;
import com.punguta.jpa.repositories.CategoryRepository;
import com.punguta.jpa.repositories.SplitRepository;
import com.punguta.jpa.repositories.TransactionRepository;
import com.punguta.services.events.expense.ExpenseCreateEvent;
import com.punguta.services.events.expense.ExpenseCreatedEvent;
import com.punguta.services.events.expense.ExpenseDeleteEvent;
import com.punguta.services.events.expense.ExpenseDeletedEvent;
import com.punguta.services.events.expense.ExpenseDetail;
import com.punguta.services.events.expense.ExpenseReadEvent;
import com.punguta.services.events.expense.ExpenseRequestReadEvent;
import com.punguta.services.events.expense.ExpenseUpdateEvent;
import com.punguta.services.events.expense.ExpenseUpdatedEvent;
import com.punguta.services.events.expense.SplitDetail;

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

    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public ExpenseCreatedEvent create(ExpenseCreateEvent expenseCreateEvent) {
        final ExpenseDetail expenseDetail = expenseCreateEvent.getDetail();

        final Book book = bookRepository.findByUserId(User.getCurrent().getId());
        final Account assetAccount = accountRepository.findOne(expenseDetail.getAssetSplit().getAccountId());
        final Expense expenseAccount = book.getExpense();

        final Transaction transaction = expenseDetail.toTransaction();

        int assetValue = 0;

        for (Split split : transaction.getSplits()) {
            split.setAccount(expenseAccount);
            final Category category = categoryRepository.findByName(split.getCategoryName());
            split.setCategory(category);
            assetValue += split.getValue();
        }

        final Split assetSplit = new Split();
        assetSplit.setAccount(assetAccount);
        assetSplit.setQty(assetValue);
        assetSplit.setValue(assetValue);
        transaction.getSplits().add(assetSplit);

        transactionRepository.save(transaction);

        final ExpenseDetail returnExpenseDetail = ExpenseDetail.fromTransaction(transaction);
        final SplitDetail assetSplitDetail = new SplitDetail();
        assetSplitDetail.setAccountId(assetAccount.getId());
        returnExpenseDetail.setAssetSplit(assetSplitDetail);

        return new ExpenseCreatedEvent(transaction.getId(), returnExpenseDetail);
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
    @Transactional
    public ExpenseReadEvent list(ExpenseRequestReadEvent expenseRequestReadEvent) {
        final List<ExpenseDetail> expenseDetails = new ArrayList<>();
        final Book book = bookRepository.findByUserId(User.getCurrent().getId());
        final Expense expenseAccount = book.getExpense();

        final Date since = expenseRequestReadEvent.getSince();

        final List<Transaction> transactions = transactionRepository.findByAccountSince(expenseAccount, since);

        for (Transaction transaction : transactions) {
            ExpenseDetail expenseDetail = new ExpenseDetail();
            expenseDetail.setPosted(transaction.getPosted());
            expenseDetail.setNote(transaction.getNote());
            for (Split split : transaction.getSplits()) {
                SplitDetail splitDetail = new SplitDetail();
                splitDetail.setNote(split.getNote());
                if (split.getAccount() instanceof Asset) {
                    splitDetail.setValue(split.getValue());
                    splitDetail.setAccountId(split.getAccount().getId());
                    expenseDetail.setAssetSplit(splitDetail);
                } else {
                    splitDetail.setValue(split.getValue());
                    splitDetail.setCategoryName(split.getCategory().getName());
                    expenseDetail.addSplitDetail(splitDetail);
                }
            }
            expenseDetails.add(expenseDetail);
        }

        ExpenseReadEvent readEvent = new ExpenseReadEvent(expenseDetails);
        return readEvent;
    }
}
