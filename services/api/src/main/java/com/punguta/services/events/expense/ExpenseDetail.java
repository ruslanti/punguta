package com.punguta.services.events.expense;

import java.util.Date;
import java.util.List;

import com.punguta.services.events.SplitDetail.SplitDetail;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class ExpenseDetail {

    private Long userId;

    private Long assetAccountId;

    private Date posted;

    private String note;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private List<SplitDetail> splits;

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

    public List<SplitDetail> getSplits() {
        return splits;
    }

    public void setSplits(List<SplitDetail> splits) {
        this.splits = splits;
    }

}
