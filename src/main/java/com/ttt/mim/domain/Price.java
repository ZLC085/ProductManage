package com.ttt.mim.domain;

import lombok.*;

import java.util.Date;

/**
 * 实体类
 * 物价（进价）
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Price {
    private Integer id;
    private Integer mId;
    private Integer factoryId;
    private Double cost;
    private Date queryTime;
    private Date createTime;
    private Date modifyTime;
}
