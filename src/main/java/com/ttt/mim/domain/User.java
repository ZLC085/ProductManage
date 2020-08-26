package com.ttt.mim.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 实体类
 * @author zl
 * date 2019/8/5 15:00
 */
public class User {
    @Getter @Setter private Integer id;
    @Getter @Setter private String username;
    @Getter @Setter private String name;
    @Getter @Setter private String password;
    @Getter @Setter private Byte gender;
    @Getter @Setter private String job;
    @Getter @Setter private Date birth;
    @Getter @Setter private Integer comId;
    @Getter @Setter private String phone;
    @Getter @Setter private String email;
    @Getter @Setter private String address;
    @Getter @Setter private Integer picId;
    @Getter @Setter private Byte status;
    @Getter @Setter private Date createTime;
    @Getter @Setter private Date modifyTime;
    @Getter @Setter private Integer groupId;
    @Getter @Setter private String newPsd;
    @Getter @Setter private String rePsd;
}