package com.ttt.mim.domain;

import lombok.*;

import java.util.Date;

/**
 * 实体类
 * 用户组关联菜单视图
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInGroup {
    private Integer user_id;
    private Integer group_id;
    private String username;
    private String name;
    private Byte gender;
    private String job;
    private Date birth;
    private Integer comId;
    private String phone;
    private String email;
    private String address;
    private Integer picId;
    private Byte status;
    private Date createTime;
    private Date modifyTime;
}
