package com.ttt.mim.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Menu {

    @Getter  @Setter private Integer id;
    @Getter  @Setter private Integer parentId;
    @Getter  @Setter private String name;
    @Getter  @Setter private String url;
    @Getter  @Setter private String perm;
    @Getter  @Setter private String icon;
    @Getter  @Setter private Date createTime;
    @Getter  @Setter private Date modifyTime;
}
