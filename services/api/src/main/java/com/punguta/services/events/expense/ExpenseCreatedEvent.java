package com.punguta.services.events.expense;

import com.punguta.services.events.CreatedEvent;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class ExpenseCreatedEvent extends CreatedEvent {
    private Long id;
    private ExpenseDetail detail;

    public ExpenseCreatedEvent(Long id, ExpenseDetail detail) {
        this.id = id;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public ExpenseDetail getDetail() {
        return detail;
    }
}
