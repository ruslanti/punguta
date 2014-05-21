package com.punguta.rest.domains;

import com.punguta.services.events.SplitDetail.SplitDetail;
import com.punguta.services.events.expense.ExpenseDetail;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class Expense {

    private Long accountId;

    private Date posted;

    private String note;

    private List<Split> splits;

    public Expense() {
    }

    public Expense(Date posted, Long accountId, String note, List<Split> splits) {
        this.splits = splits;
        this.accountId = accountId;
        this.posted = posted;
        this.note = note;
    }

    public static Expense fromExpenseDetail(ExpenseDetail expenseDetail) {
        Expense expense = new Expense();
        BeanUtils.copyProperties(expenseDetail, expense, "splits");
        List<SplitDetail> expenseDetailSplits = expenseDetail.getSplits();
        expense.splits = new ArrayList<Split>(expenseDetailSplits.size());
        for (SplitDetail splitDetail : expenseDetailSplits) {
            expense.splits.add(Split.fromSplitDetail(splitDetail));
        }

        return expense;
    }

    public ExpenseDetail toExpenseDetail() {
        ExpenseDetail expenseDetail = new ExpenseDetail();
        BeanUtils.copyProperties(this, expenseDetail, "splits");
        List<SplitDetail> splitDetails = new ArrayList<SplitDetail>(splits.size());
        for (Split split : splits) {
            splitDetails.add(split.toSplitDetail());
        }
        expenseDetail.setSplits(splitDetails);

        return expenseDetail;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

}
