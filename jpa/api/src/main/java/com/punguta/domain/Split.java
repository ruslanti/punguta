package com.punguta.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.punguta.domain.account.Account;

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
