package com.punguta.services.events.expense;

import com.punguta.services.events.RequestReadEvent;

import java.time.Period;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class ExpenseRequestReadEvent extends RequestReadEvent{
    private final Period period;

    public ExpenseRequestReadEvent() {
        this.period = Period.ofWeeks(1);
    }

    public ExpenseRequestReadEvent(Period period) {
        this.period = period;
    }

    public Period getPeriod() {
        return period;
    }
}
