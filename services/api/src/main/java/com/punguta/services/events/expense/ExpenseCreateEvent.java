package com.punguta.services.events.expense;

import com.punguta.services.events.CreateEvent;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class ExpenseCreateEvent extends CreateEvent{
    private ExpenseDetail detail;

    public ExpenseCreateEvent(ExpenseDetail detail) {
        this.detail = detail;
    }

    public ExpenseDetail getDetail() {
        return detail;
    }
}
