package com.punguta.jpa.domains;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Entity
public class Split extends AbstractEntity {

    private int qty;

    private int value;

    @ManyToOne
    private Category category;

    private String note;

    @ManyToOne
    private Account account;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Split{" +
                "qty=" + qty +
                ", value=" + value +
                ", category=" + category +
                ", note='" + note + '\'' +
                ", account=" + account +
                '}';
    }
}
