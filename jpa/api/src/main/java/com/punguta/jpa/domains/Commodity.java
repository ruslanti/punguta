package com.punguta.jpa.domains;

import javax.persistence.*;

/**
 * User: ruslan
 * Date: 5/15/14
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "namespace", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("COMMODITY")
public class Commodity extends AbstractEntity {

    private String code;

    private String fullname;

    private int denom;

    private String unit;

    private String format;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"{" +
                "code='" + code + '\'' +
                ", fullname='" + fullname + '\'' +
                ", denom=" + denom +
                ", unit='" + unit + '\'' +
                ", format='" + format + '\'' +
                '}' ;
    }
}
