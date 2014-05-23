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

    private Long userId;

    private Long assetAccountId;

    private Date posted;

    private String note;

    private SplitDetail assetSplit;

    private Set<SplitDetail> splitDetails;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAssetAccountId() {
        return assetAccountId;
    }

    public void setAssetAccountId(Long assetAccountId) {
        this.assetAccountId = assetAccountId;
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

    public Set<SplitDetail> getSplitDetails() {
        return splitDetails;
    }

    public void setSplitDetails(Set<SplitDetail> splitDetails) {
        this.splitDetails = splitDetails;
    }

    public SplitDetail getAssetSplit() {
        return assetSplit;
    }

    public void setAssetSplit(SplitDetail assetSplit) {
        this.assetSplit = assetSplit;
    }

    public void addSplitDetail(SplitDetail splitDetail) {
        if (splitDetails == null) {
            splitDetails = new HashSet<>();
        }
        splitDetails.add(splitDetail);
    }

    public Transaction toTransaction() {
        final Transaction transaction = new Transaction();
        transaction.setDate(posted);
        transaction.setNote(note);
        transaction.setSplits(splitDetails.stream().map(
                SplitDetail::toSplit).collect(Collectors.toSet()));
        return transaction;
    }

    public static ExpenseDetail fromTransaction(Transaction transaction) {
        final ExpenseDetail expenseDetail = new ExpenseDetail();
        expenseDetail.setPosted(transaction.getDate());
        expenseDetail.setNote(transaction.getNote());
        expenseDetail.setSplitDetails(transaction.getSplits().stream().map(
                SplitDetail::fromSplit).collect(Collectors.toSet()));
        return expenseDetail;
    }
}
