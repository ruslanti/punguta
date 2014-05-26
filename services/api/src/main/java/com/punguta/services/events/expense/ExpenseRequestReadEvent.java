package com.punguta.services.events.expense;

import java.util.Calendar;
import java.util.Date;

import com.punguta.services.events.RequestReadEvent;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class ExpenseRequestReadEvent extends RequestReadEvent{
    private final Date since;

    public ExpenseRequestReadEvent() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.WEEK_OF_YEAR, -1);
        this.since = now.getTime();
    }

    public ExpenseRequestReadEvent(Date period) {
        this.since = period;
    }

    public Date getSince() {
        return since;
    }
}
