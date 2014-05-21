package com.punguta.services;

import com.punguta.services.events.expense.*;

/**
 * Created by ruslanti on 21.05.2014.
 */
public interface ExpenseService {

    public ExpenseCreatedEvent create(ExpenseCreateEvent expenseCreateEvent);

    public ExpenseUpdatedEvent update(ExpenseUpdateEvent expenseUpdateEvent);

    public ExpenseDeletedEvent delete(ExpenseDeleteEvent expenseDeleteEvent);

    public ExpenseReadEvent list(ExpenseRequestReadEvent expenseRequestReadEvent);
}
