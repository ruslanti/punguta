package com.punguta.rest;

import com.punguta.services.events.expense.ExpenseCreatedEvent;
import com.punguta.services.events.expense.ExpenseDetail;
import com.punguta.services.events.expense.ExpenseReadEvent;

import java.util.ArrayList;
import java.util.List;

import static com.punguta.rest.RestDetailsFixture.generateExpenseDetail;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class RestEventFixtures {

    public static ExpenseCreatedEvent expenseCreated(Long id) {
        return new ExpenseCreatedEvent(id, generateExpenseDetail());
    }

    public static ExpenseReadEvent generateExpenses(int num) {
        List<ExpenseDetail> expenseDetailList = new ArrayList<ExpenseDetail>(num);
        for (int i = 0; i < num; i++) {
            expenseDetailList.add(generateExpenseDetail());
        }
        return new ExpenseReadEvent(expenseDetailList);
    }

    public static String standardExpenseJSON() {
        return "{\"accountId\":1,\"posted\":1400707218479,\"note\":\"Note\",\"splits\":[{\"value\":null,\"currency\":null},{\"value\":null,\"currency\":null}]}";
    }
}
