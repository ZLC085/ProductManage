package com.ttt.mim.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.Date;

@Data
@NoArgsConstructor
public class PurchaseDetails {
    private String indentNum;
    private String purchaseNum;
    private String purchaseName;
    private String purchaseRemark;
    private String indentName;
    private Double sum;
    private Integer factoryId;
    private Date createTime;
    private Date modifyTime;
    private Integer approvalId;
    private Byte approvalResult;
    private String approvalOpinion;
    private Date approvalTime;
    private String approvalName;
    private String factoryName;
    private String factoryPhone;
    private String factorySocialCode;
    private String factoryLegalPerson;
    private String factoryType;
}
