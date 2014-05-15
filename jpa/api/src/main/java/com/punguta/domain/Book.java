package com.punguta.domain;

import javax.persistence.Entity;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Entity
public class Book extends AbstractEntity {

    private Commodity defaultCommodity;

    public Commodity getDefaultCommodity() {
        return defaultCommodity;
    }

    public void setDefaultCommodity(Commodity defaultCommodity) {
        this.defaultCommodity = defaultCommodity;
    }
}
