package com.punguta.rest.domains;

import org.springframework.beans.BeanUtils;

import com.punguta.services.events.details.SplitDetail;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class Split {
    private int value;

    private String currency;

    public Split() {
    }

    public static Split fromSplitDetail(SplitDetail splitDetail) {
        Split split = new Split();
        BeanUtils.copyProperties(splitDetail, split);
        return split;
    }

    public SplitDetail toSplitDetail() {
        SplitDetail splitDetail = new SplitDetail();
        BeanUtils.copyProperties(this, splitDetail);
        return splitDetail;
    }


    public Integer getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }
}
