package com.ttt.mim.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PurchaseOutData {

    private String indentNum;
    private String purchaseNum;
    private String purchaseName;
    private String purchaseRemark;
    private String indentName;
    private Double sum;
    private String factoryName;
    private Date createTime;
    private Byte approvalResult;
    private String approvalName;
}
