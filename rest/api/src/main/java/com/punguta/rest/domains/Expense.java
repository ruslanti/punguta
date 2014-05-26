package com.punguta.rest.domains;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.punguta.services.events.expense.ExpenseDetail;
import com.punguta.services.events.expense.SplitDetail;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class Expense {

    private Long accountId;

    private Date posted;

    private String note;

    private Set<Split> splits;

    public static Expense fromExpenseDetail(ExpenseDetail expenseDetail) {
        Expense expense = new Expense();
        BeanUtils.copyProperties(expenseDetail, expense, "splits");
        Set<SplitDetail> expenseDetailSplits = expenseDetail.getExpenseSplits();
        expense.splits = expenseDetailSplits.stream().map(
                Split::fromSplitDetail).collect(Collectors.toSet());
        return expense;
    }

    public ExpenseDetail toExpenseDetail() {
        ExpenseDetail expenseDetail = new ExpenseDetail();
        BeanUtils.copyProperties(this, expenseDetail, "splits");
        expenseDetail.setExpenseSplits(splits.stream().map(
                Split::toSplitDetail).collect(Collectors.toSet()));
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

    public Set<Split> getSplits() {
        return splits;
    }

    public void setSplits(Set<Split> splits) {
        this.splits = splits;
    }
}
