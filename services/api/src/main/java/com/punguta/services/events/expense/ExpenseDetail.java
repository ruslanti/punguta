package com.punguta.services.events.expense;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.punguta.jpa.domains.Transaction;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class ExpenseDetail {

    private Date posted;

    private String note;

    private SplitDetail assetSplit;

    private Set<SplitDetail> expenseSplits;

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

    public Set<SplitDetail> getExpenseSplits() {
        return expenseSplits;
    }

    public void setExpenseSplits(Set<SplitDetail> expenseSplits) {
        this.expenseSplits = expenseSplits;
    }

    public SplitDetail getAssetSplit() {
        return assetSplit;
    }

    public void setAssetSplit(SplitDetail assetSplit) {
        this.assetSplit = assetSplit;
    }

    public void addSplitDetail(SplitDetail splitDetail) {
        if (expenseSplits == null) {
            expenseSplits = new HashSet<>();
        }
        expenseSplits.add(splitDetail);
    }

    public Transaction toTransaction() {
        final Transaction transaction = new Transaction();
        transaction.setPosted(posted);
        transaction.setNote(note);
        transaction.setSplits(expenseSplits.stream().map(
                SplitDetail::toSplit).collect(Collectors.toSet()));
        return transaction;
    }

    public static ExpenseDetail fromTransaction(Transaction transaction) {
        final ExpenseDetail expenseDetail = new ExpenseDetail();
        expenseDetail.setPosted(transaction.getPosted());
        expenseDetail.setNote(transaction.getNote());
        expenseDetail.setExpenseSplits(transaction.getSplits().stream().map(
                SplitDetail::fromSplit).collect(Collectors.toSet()));
        return expenseDetail;
    }
}
