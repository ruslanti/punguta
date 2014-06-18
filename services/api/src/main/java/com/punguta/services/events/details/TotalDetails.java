package com.punguta.services.events.details;

import com.punguta.jpa.domains.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruslan on 6/18/14.
 */
public class TotalDetails {
    Map<Account, Long> totals;

    public void addTotal(Account account, Long total) {
        if (totals == null) {
            totals = new HashMap<>();
        }
        totals.put(account, total);
    }

    public Map<Account, Long> getTotals() {
        return totals;
    }
}
