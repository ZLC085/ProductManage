package com.ttt.mim.domain;

import lombok.*;

import java.util.Date;
/**
 * 实体类
 * 用户组
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Group {
    private Integer id;
    private String groupName;
    private String remark;
    private Date createTime;
    private Date modifyTime;
}
