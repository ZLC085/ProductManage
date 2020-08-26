package com.ttt.mim.domain;

import lombok.Data;

import java.util.Date;

/**
 * 物资
 * @author : mao yuhang
 * date : 2019-08-19 16:55
 */
@Data
public class Material {
    private Integer id;
    private String name;
    private Integer parent_id;
    private String spec;
    private String unit;
    private String purpose;
    private String use_unit;
    private Integer pic_id;
    private Date create_time;
    private Date modify_time;
}
