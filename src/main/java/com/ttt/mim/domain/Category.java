package com.ttt.mim.domain;

import lombok.Data;

import java.util.Date;

/**
 * 物资分类
 * @author : mao yuhang
 * date : 2019-08-19 16:51
 */
@Data
public class Category {
    private Integer id;
    private Integer parent_id;
    private String name;
    private Date create_time;
    private Date modify_time;
}
