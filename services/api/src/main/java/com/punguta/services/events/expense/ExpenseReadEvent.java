package com.punguta.services.events.expense;

import com.punguta.services.events.ReadEvent;

import java.util.List;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class ExpenseReadEvent extends ReadEvent{
    private final List<ExpenseDetail> expenseDetails;

    public ExpenseReadEvent(List<ExpenseDetail> expenseDetails) {
        this.expenseDetails = expenseDetails;
    }

    public List<ExpenseDetail> getDetails() {
        return expenseDetails;
    }
}
