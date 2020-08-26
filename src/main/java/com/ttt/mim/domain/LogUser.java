package com.ttt.mim.domain;

import lombok.*;

import java.util.Date;

/**
 * @Description 用户日志实体类
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogUser {
    private int id;
    private int userId;
    private String userName;
    private String operation;
    private String ip;
    private Date createTime;
}
