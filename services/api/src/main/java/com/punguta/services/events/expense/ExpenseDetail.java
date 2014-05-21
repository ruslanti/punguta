package com.punguta.services.events.expense;

import com.punguta.services.events.SplitDetail.SplitDetail;

import java.util.Date;
import java.util.List;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class ExpenseDetail {

    private Long accountId;

    private Date posted;

    private String note;

    private List<SplitDetail> splits;

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

    public List<SplitDetail> getSplits() {
        return splits;
    }

    public void setSplits(List<SplitDetail> splits) {
        this.splits = splits;
    }

}
