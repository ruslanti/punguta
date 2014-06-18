package com.punguta.rest.domains;

import com.punguta.jpa.domains.*;
import com.punguta.services.events.details.TotalDetails;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class Totals {
    long incomes;
    long expenses;
    long totals;

    long loans;
    long liabilities;

    Map<String, Long> assets = new HashMap<>();

    public long getIncomes() {
        return incomes;
    }

    public static Totals fromTotalDetails(TotalDetails totalDetails) {
        Totals totals = new Totals();

        for (Map.Entry<Account, Long> entry : totalDetails.getTotals().entrySet()) {
            if (entry.getKey() instanceof com.punguta.jpa.domains.Income) {
                totals.incomes = entry.getValue();
            } else if (entry.getKey() instanceof com.punguta.jpa.domains.Expense) {
                totals.expenses = entry.getValue();
            } else if (entry.getKey() instanceof  Loan) {
                totals.loans = entry.getValue();
            } else if (entry.getKey() instanceof Liability) {
                totals.liabilities = entry.getValue();
            } else if (entry.getKey() instanceof Asset) {
                totals.assets.put(entry.getKey().getName(), entry.getValue());
            } else {
                assert true;
            }
        }

        return totals;
    }

    public long getExpenses() {
        return expenses;
    }

    public long getTotals() {
        return totals;
    }

    public long getLoans() {
        return loans;
    }

    public long getLiabilities() {
        return liabilities;
    }

    public Map<String, Long> getAssets() {
        return assets;
    }
}
