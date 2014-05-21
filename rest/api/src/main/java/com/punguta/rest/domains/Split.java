package com.punguta.rest.domains;

import com.punguta.services.events.SplitDetail.SplitDetail;
import com.punguta.services.events.expense.ExpenseDetail;
import org.springframework.beans.BeanUtils;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class Split {
    private Integer value;

    private String currency;

    public Split() {
    }

    public Split(Integer value, String currency) {
        this.value = value;
        this.currency = currency;
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
