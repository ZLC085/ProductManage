package com.ttt.mim.domain;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class Factory {
    /**厂家id*/
    @Getter @Setter private Integer id;
    /**厂家名字*/
    @Getter @Setter private String name;
    /**联系方式*/
    @Getter @Setter private String phone;
    @Getter @Setter private String socialCode;
    @Getter @Setter private String regNum;
    @Getter @Setter private String type;
    @Getter @Setter private String legalPerson;
    @Getter @Setter private  String regCapital;
    @Getter @Setter private  String regAgency;
    @Getter @Setter private String orgCode;
    @Getter @Setter private String status;
    @Getter @Setter private String industry;
    @Getter @Setter private Date establishDate;
    @Getter @Setter private Date runStart;
    @Getter @Setter private Date runEnd;
    @Getter @Setter private Date releaseLicenseTime;
    @Getter @Setter private String address;
    @Getter @Setter private String busScope;
    @Getter @Setter private Date createTime;
    @Getter @Setter private Date modifyTime;

}
