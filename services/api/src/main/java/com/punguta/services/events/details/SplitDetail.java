package com.punguta.services.events.details;

import com.punguta.jpa.domains.Deposit;
import com.punguta.jpa.domains.Split;
import com.punguta.jpa.domains.Withdrawal;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class SplitDetail {
    private int value;

    private String categoryName;

    private String note;

    private Long accountId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Deposit toDeposit() {
        final Deposit split = new Deposit();
        split.setValue(value);
        split.setQty(value);
        split.setNote(note);
        split.setCategoryName(categoryName);
        return split;
    }

    public static SplitDetail fromDeposit(Deposit split) {
        final SplitDetail splitDetail = new SplitDetail();
        splitDetail.setCategoryName(split.getCategoryName());
        splitDetail.setValue(split.getValue());
        splitDetail.setNote(split.getNote());
        return splitDetail;
    }

    public Withdrawal toWithdrawal() {
        final Withdrawal split = new Withdrawal();
        split.setValue(value);
        split.setQty(value);
        split.setNote(note);
        split.setCategoryName(categoryName);
        return split;
    }

    public static SplitDetail fromWithdrawal(Withdrawal split) {
        final SplitDetail splitDetail = new SplitDetail();
        splitDetail.setCategoryName(split.getCategoryName());
        splitDetail.setValue(split.getValue());
        splitDetail.setNote(split.getNote());
        return splitDetail;
    }
}
