package com.ttt.mim.domain;

import lombok.*;

import java.util.Date;

/**
 * @Description 系统日志实体类
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogSys {
    private int id;
    private String msg;
    private Date createTime;
}
