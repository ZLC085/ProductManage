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
public class MenuInGroup {
    private Integer groupId;
    private Integer menuId;
    private Integer parentId;
    private String name;
    private String url;
    private String perm;
    private String icon;
    private Date createTime;
    private Date modifyTime;
}
