package com.punguta.services.events.SplitDetail;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class SplitDetail {
    private Integer value;

    private String currency;

    private String note;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
