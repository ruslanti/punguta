package com.punguta.rest;

import com.punguta.rest.domains.Expense;
import com.punguta.services.ExpenseService;
import com.punguta.services.events.expense.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruslanti on 21.05.2014.
 */
@Controller
@RequestMapping("/expenses")
public class ExpenseController {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Expense> list() {
        List<Expense> orders = new ArrayList<Expense>();

        ExpenseRequestReadEvent expenseRequestReadEvent = new ExpenseRequestReadEvent();

        ExpenseReadEvent expenseReadEvent = expenseService.list(expenseRequestReadEvent);

        for (ExpenseDetail expenseDetail : expenseReadEvent.getDetails()) {
            orders.add(Expense.fromExpenseDetail(expenseDetail));
        }

        return orders;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Expense> spend(@RequestBody Expense expense) {
        ExpenseCreatedEvent createdEvent = expenseService.create(new ExpenseCreateEvent(expense.toExpenseDetail()));
        Expense newExpense = Expense.fromExpenseDetail(createdEvent.getDetail());

        return new ResponseEntity<Expense>(newExpense, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Expense> delete(@PathVariable String id) {

        ExpenseDeletedEvent deletedEvent = expenseService.delete(new ExpenseDeleteEvent(Long.valueOf(id)));

        if (!deletedEvent.isEntityFound()) {
            return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);
        }

        Expense deletedExpense = Expense.fromExpenseDetail(deletedEvent.getDetail());

        return new ResponseEntity<Expense>(deletedExpense, HttpStatus.OK);
    }
}
