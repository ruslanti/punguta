package com.punguta.domain;

import java.util.Date;

import javax.persistence.Entity;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Entity
public class Price extends AbstractEntity {

    private Date since;

    private int value;

    private int denom;

    private Commodity commodity;

    private Commodity currency;

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDenom() {
        return denom;
    }

    public void setDenom(int denom) {
        this.denom = denom;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Commodity getCurrency() {
        return currency;
    }

    public void setCurrency(Commodity currency) {
        this.currency = currency;
    }
}
