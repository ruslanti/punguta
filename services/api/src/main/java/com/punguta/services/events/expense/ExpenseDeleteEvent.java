package com.punguta.services.events.expense;

import com.punguta.services.events.DeleteEvent;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class ExpenseDeleteEvent extends DeleteEvent{
    private final Long id;

    public ExpenseDeleteEvent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
