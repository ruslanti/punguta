package com.punguta.jpa.domains;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Entity
public class Transaction extends AbstractEntity {

    private Date posted;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Split> splits;

    private String note;

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

    public Set<Split> getSplits() {
        return splits;
    }

    public void setSplits(Set<Split> splits) {
        this.splits = splits;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "posted=" + posted +
                ", splits=" + splits +
                ", note='" + note + '\'' +
                '}';
    }
}
