package com.punguta.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.punguta.rest.domains.Expense;
import com.punguta.services.ExpenseService;
import com.punguta.services.events.expense.ExpenseCreateEvent;
import com.punguta.services.events.expense.ExpenseCreatedEvent;
import com.punguta.services.events.expense.ExpenseDeleteEvent;
import com.punguta.services.events.expense.ExpenseDeletedEvent;
import com.punguta.services.events.expense.ExpenseReadEvent;
import com.punguta.services.events.expense.ExpenseRequestReadEvent;

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
        List<Expense> orders = new ArrayList<>();

        ExpenseRequestReadEvent expenseRequestReadEvent = new ExpenseRequestReadEvent();

        ExpenseReadEvent expenseReadEvent = expenseService.list(expenseRequestReadEvent);

        orders.addAll(expenseReadEvent.getDetails().stream().map(Expense::fromExpenseDetail).collect(Collectors.toList()));

        return orders;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Expense> spend(@RequestBody Expense expense) {
        ExpenseCreatedEvent createdEvent = expenseService.create(new ExpenseCreateEvent(expense.toExpenseDetail()));
        Expense newExpense = Expense.fromExpenseDetail(createdEvent.getDetail());

        return new ResponseEntity<>(newExpense, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Expense> delete(@PathVariable String id) {

        ExpenseDeletedEvent deletedEvent = expenseService.delete(new ExpenseDeleteEvent(Long.valueOf(id)));

        if (!deletedEvent.isEntityFound()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Expense deletedExpense = Expense.fromExpenseDetail(deletedEvent.getDetail());

        return new ResponseEntity<>(deletedExpense, HttpStatus.OK);
    }
}
