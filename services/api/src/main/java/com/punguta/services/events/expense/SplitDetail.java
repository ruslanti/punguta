package com.punguta.services.events.expense;

import com.punguta.jpa.domains.Split;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class SplitDetail {
    private Integer value;

    private String categoryName;

    private String note;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
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

    public Split toSplit() {
        final Split split = new Split();
        split.setValue(value);
        split.setQty(value);
        split.setNote(note);
        split.setCategoryName(categoryName);
        return split;
    }

    public static SplitDetail fromSplit(Split split) {
        final SplitDetail splitDetail = new SplitDetail();
        splitDetail.setCategoryName(split.getCategoryName());
        splitDetail.setValue(split.getValue());
        splitDetail.setNote(split.getNote());
        return splitDetail;
    }
}
