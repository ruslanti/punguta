package com.punguta.services.events.details;

import com.punguta.jpa.domains.Deposit;
import com.punguta.jpa.domains.Split;
import com.punguta.jpa.domains.Transaction;
import com.punguta.jpa.domains.Withdrawal;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by ruslanti on 26.05.2014.
 */
public class ExpenseDetail {

    Date posted;

    String note;

    SplitDetail withdrawalDetail;

    Set<SplitDetail> depositDetails;

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

    public Set<SplitDetail> getDepositDetails() {
        return depositDetails;
    }

    public void setDepositDetails(Set<SplitDetail> depositDetails) {
        this.depositDetails = depositDetails;
    }

    public SplitDetail getWithdrawalDetail() {
        return withdrawalDetail;
    }

    public void setWithdrawalDetail(SplitDetail withdrawalDetail) {
        this.withdrawalDetail = withdrawalDetail;
    }


    public Transaction toTransaction() {
        final Transaction transaction = new Transaction();
        transaction.setPosted(posted);
        transaction.setNote(note);
        transaction.setSplits(depositDetails.stream().map(
                SplitDetail::toDeposit).collect(Collectors.toSet()));
       // transaction.getSplits().add(withdrawalDetail.toWithdrawal());
        return transaction;
    }

    public static ExpenseDetail fromTransaction(Transaction transaction) {
        final ExpenseDetail expenseDetail = new ExpenseDetail();
        expenseDetail.setPosted(transaction.getPosted());
        expenseDetail.setNote(transaction.getNote());
        Set<SplitDetail> depositDetails = new HashSet<>(transaction.getSplits().size());
        for (Split split : transaction.getSplits()) {
            if (split instanceof Withdrawal) {
                expenseDetail.setWithdrawalDetail(SplitDetail.fromWithdrawal((Withdrawal) split));
            } else
            if (split instanceof Deposit) {
                depositDetails.add(SplitDetail.fromDeposit((Deposit) split));
            }
        }
        expenseDetail.setDepositDetails(depositDetails);
        return expenseDetail;
    }
}
