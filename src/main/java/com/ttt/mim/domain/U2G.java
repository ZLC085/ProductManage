package com.ttt.mim.domain;

import lombok.*;

/**
 * 实体类
 * 用户关联用户组
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class U2G {
    private Integer id;
    private Integer user_id;
    private Integer group_id;
}
