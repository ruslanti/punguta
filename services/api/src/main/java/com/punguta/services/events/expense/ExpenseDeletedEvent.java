package com.punguta.services.events.expense;

import com.punguta.services.events.DeletedEvent;
import com.punguta.services.events.details.ExpenseDetail;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class ExpenseDeletedEvent extends DeletedEvent{

    private final Long id;
    private ExpenseDetail expenseDetail;

    public ExpenseDeletedEvent(Long id) {
        this.id = id;
    }

    public ExpenseDeletedEvent(Long id, ExpenseDetail expenseDetail) {
        this.id = id;
        this.expenseDetail = expenseDetail;
    }

    public ExpenseDetail getDetail() {
        return expenseDetail;
    }

    public static ExpenseDeletedEvent notFound(Long id) {
        ExpenseDeletedEvent deletedEvent = new ExpenseDeletedEvent(id);
        deletedEvent.entityFound = false;
        return deletedEvent;
    }
}
