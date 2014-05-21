package com.punguta.services;

import com.punguta.services.events.expense.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ruslanti on 21.05.2014.
 */
@Service
public class ExpenseServiceImpl implements ExpenseService{
    private static final Logger logger = LoggerFactory.getLogger(ExpenseServiceImpl.class);

    @Override
    public ExpenseCreatedEvent create(ExpenseCreateEvent expenseCreateEvent) {
        return new ExpenseCreatedEvent(1L, expenseCreateEvent.getDetail());
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
