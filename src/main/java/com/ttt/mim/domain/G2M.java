package com.ttt.mim.domain;

import lombok.*;

/**
 * 实体类
 * 用户组关联菜单
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class G2M {
    private Integer id;
    private Integer group_id;
    private Integer menu_id;
}
