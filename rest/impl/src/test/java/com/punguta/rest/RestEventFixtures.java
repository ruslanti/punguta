package com.punguta.rest;

import static com.punguta.rest.RestDetailsFixture.generateExpenseDetail;

import java.util.ArrayList;
import java.util.List;

import com.punguta.services.events.details.ExpenseDetail;
import com.punguta.services.events.expense.ExpenseCreatedEvent;
import com.punguta.services.events.expense.ExpenseReadEvent;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class RestEventFixtures {

    public static ExpenseCreatedEvent expenseCreated(Long id) {
        return new ExpenseCreatedEvent(id, generateExpenseDetail());
    }

    public static ExpenseReadEvent generateExpenses(int num) {
        List<ExpenseDetail> expenseDetails = new ArrayList<ExpenseDetail>(num);
        for (int i = 0; i < num; i++) {
            expenseDetails.add(generateExpenseDetail());
        }
        return new ExpenseReadEvent(expenseDetails);
    }

    public static String standardExpenseJSON() {
        return "{\"posted\":1400707218479,\"note\":\"Note\",\"splits\":[{\"value\":null,\"currency\":null},{\"value\":null,\"currency\":null}]}";
    }
}
