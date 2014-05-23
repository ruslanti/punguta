package com.punguta.rest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.punguta.services.events.expense.ExpenseDetail;
import com.punguta.services.events.expense.SplitDetail;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class RestDetailsFixture {

    public static SplitDetail generateSplitDetail() {
        SplitDetail splitDetail = new SplitDetail();
        splitDetail.setValue(10);
        return splitDetail;
    }

    public static ExpenseDetail generateExpenseDetail() {
        ExpenseDetail expenseDetail = new ExpenseDetail();
        expenseDetail.setAssetAccountId(1L);
        expenseDetail.setPosted(new Date());
        expenseDetail.setNote("Note");
        Set<SplitDetail> splitDetails = new HashSet<>(2);
        for (int i = 0; i < 2; i++) {
            splitDetails.add(generateSplitDetail());
        }
        expenseDetail.setSplitDetails(splitDetails);
        return expenseDetail;
    }
}
