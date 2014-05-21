package com.punguta.services.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.punguta.jpa.domains.Asset;
import com.punguta.jpa.domains.Commodity;
import com.punguta.jpa.domains.Expense;
import com.punguta.jpa.domains.Income;
import com.punguta.jpa.domains.Liability;
import com.punguta.jpa.domains.Loan;
import com.punguta.jpa.repositories.AccountRepository;

/**
 * User: ruslan
 * Date: 5/19/14
 */
@Component
public class AccountFactory {

    @Autowired
    private AccountRepository accountRepository;

    public Asset createAsset(String code, String name, String description, Commodity commodity) {
        Asset asset = new Asset();
        asset.setCode(code);
        asset.setName(name);
        asset.setDescription(description);
        asset.setCommodity(commodity);
        asset.setHidden(false);
        accountRepository.save(asset);
        return asset;
    }

    public Asset createAsset(Commodity commodity) {
        return createAsset("WL", "Income", "Generic wallet account", commodity);
    }

    public Income createIncome(Commodity commodity) {
        Income income = new Income();
        income.setCode("IN");
        income.setName("Income");
        income.setDescription("Generic income account");
        income.setCommodity(commodity);
        income.setHidden(false);
        accountRepository.save(income);
        return income;
    }

    public Expense createExpense(Commodity commodity) {
        Expense expense = new Expense();
        expense.setCode("EX");
        expense.setName("Expense");
        expense.setDescription("Generic expense account");
        expense.setCommodity(commodity);
        expense.setHidden(false);
        accountRepository.save(expense);
        return expense;
    }

    public Loan createLoan(Commodity commodity) {
        Loan loan = new Loan();
        loan.setCode("LO");
        loan.setName("Loan");
        loan.setDescription("Generic loan account");
        loan.setCommodity(commodity);
        loan.setHidden(false);
        accountRepository.save(loan);
        return loan;
    }

    public Liability createLiability(Commodity commodity) {
        Liability liability = new Liability();
        liability.setCode("LI");
        liability.setName("Liability");
        liability.setDescription("Generic liability account");
        liability.setCommodity(commodity);
        liability.setHidden(false);
        accountRepository.save(liability);
        return liability;
    }

}
