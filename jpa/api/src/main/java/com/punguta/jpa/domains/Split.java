package com.punguta.jpa.domains;

import javax.persistence.*;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("split")
public class Split extends AbstractEntity {

    private int qty;

    private int value;

    @Transient
    private String categoryName;

    @ManyToOne(cascade = CascadeType.REFRESH)
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Split{" +
                "qty=" + qty +
                ", value=" + value +
                ", category=" + categoryName +
                ", note='" + note + '\'' +
                ", account=" + account +
                '}';
    }
}
