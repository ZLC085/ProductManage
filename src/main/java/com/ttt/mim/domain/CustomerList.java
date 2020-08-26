package com.ttt.mim.domain;

import lombok.*;

import java.util.Date;

/**
 * 客户管理
 * @author JY
 */
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class CustomerList {
    private Integer id;
    private String name;
    private String legalPerson;
    private String phone;
    private String industry;
    private String credit;
    private String address;
    private Date createTime;
    private Date modifyTime;
}
