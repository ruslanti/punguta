package com.punguta.domain;

import java.util.Date;

import javax.persistence.Entity;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Entity
public class Commodity extends AbstractEntity {

    private Date posted;

    private String code;

    private String namespace;

    private String fullname;

    private int denom;

    private String unit;

    private String format;

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getDenom() {
        return denom;
    }

    public void setDenom(int denom) {
        this.denom = denom;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
