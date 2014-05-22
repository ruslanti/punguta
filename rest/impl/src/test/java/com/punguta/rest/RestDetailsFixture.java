package com.punguta.rest;

import com.punguta.services.events.SplitDetail.SplitDetail;
import com.punguta.services.events.expense.ExpenseDetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class RestDetailsFixture {

    public static SplitDetail generateSplitDetail() {
        SplitDetail splitDetail = new SplitDetail();
        splitDetail.setValue(10);
        splitDetail.setCurrency("USD");
        return splitDetail;
    }

    public static ExpenseDetail generateExpenseDetail() {
        ExpenseDetail expenseDetail = new ExpenseDetail();
        expenseDetail.setAssetAccountId(1L);
        expenseDetail.setPosted(new Date());
        expenseDetail.setNote("Note");
        List<SplitDetail> splitDetails = new ArrayList<SplitDetail>(2);
        for (int i = 0; i < 2; i++) {
            splitDetails.add(generateSplitDetail());
        }
        expenseDetail.setSplits(splitDetails);
        return expenseDetail;
    }
}
