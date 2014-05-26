package com.punguta.services;

import java.util.*;

import javax.annotation.Resource;

import com.punguta.jpa.domains.*;
import com.punguta.services.events.details.ExpenseDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.punguta.jpa.repositories.AccountRepository;
import com.punguta.jpa.repositories.BookRepository;
import com.punguta.jpa.repositories.CategoryRepository;
import com.punguta.jpa.repositories.SplitRepository;
import com.punguta.jpa.repositories.TransactionRepository;
import com.punguta.services.events.expense.ExpenseCreateEvent;
import com.punguta.services.events.expense.ExpenseCreatedEvent;
import com.punguta.services.events.expense.ExpenseDeleteEvent;
import com.punguta.services.events.expense.ExpenseDeletedEvent;
import com.punguta.services.events.expense.ExpenseReadEvent;
import com.punguta.services.events.expense.ExpenseRequestReadEvent;
import com.punguta.services.events.expense.ExpenseUpdateEvent;
import com.punguta.services.events.expense.ExpenseUpdatedEvent;
import com.punguta.services.events.details.SplitDetail;

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
        final Account assetAccount = accountRepository.findOne(expenseDetail.getWithdrawalDetail().getAccountId());
        final Expense expenseAccount = book.getExpense();

        Transaction transaction = expenseDetail.toTransaction();

        int assetValue = 0;

        for (Split deposit : transaction.getSplits()) {
            deposit.setAccount(expenseAccount);
            final Category category = categoryRepository.findByName(deposit.getCategoryName());
            deposit.setCategory(category);
            assetValue += deposit.getValue();
        }

        final Withdrawal withdrawal = new Withdrawal();
        withdrawal.setAccount(assetAccount);
        withdrawal.setQty(assetValue);
        withdrawal.setValue(assetValue);
        transaction.getSplits().add(withdrawal);

        transaction = transactionRepository.save(transaction);

        final ExpenseDetail returnExpenseDetail = ExpenseDetail.fromTransaction(transaction);
/*        final SplitDetail withdrawalDetail = new SplitDetail();
        withdrawalDetail.setAccountId(assetAccount.getId());
        returnExpenseDetail.setWithdrawalDetail(withdrawalDetail);*/

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

            Set<SplitDetail> depositDetails = new HashSet<>(transaction.getSplits().size());
            for (Split split : transaction.getSplits()) {
                SplitDetail splitDetail = new SplitDetail();
                splitDetail.setValue(split.getValue());
                if (split instanceof Withdrawal) {
                    splitDetail.setValue(split.getValue());
                    splitDetail.setAccountId(split.getAccount().getId());
                    expenseDetail.setWithdrawalDetail(splitDetail);
                } else if (split instanceof Deposit) {
                    splitDetail.setCategoryName(split.getCategory().getName());
                    splitDetail.setNote(split.getNote());
                    depositDetails.add(splitDetail);
                }
            }

            expenseDetail.setDepositDetails(depositDetails);
            expenseDetails.add(expenseDetail);
        }

        ExpenseReadEvent readEvent = new ExpenseReadEvent(expenseDetails);
        return readEvent;
    }
}
