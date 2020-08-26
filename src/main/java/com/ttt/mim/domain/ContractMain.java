package com.ttt.mim.domain;

import lombok.Data;

import java.util.Date;

/**
 * 合同生成  实体类
 * @author zl
 * date 2019/8/5 15:00
 */
@Data
public class ContractMain {
    private Integer id;
    private String num;
    private String filename;
    private byte[] file;
    private Long filesize;
    private Integer customerId;
    private Double cost;
    private Double quote;
    private Boolean status;
    private Date createTime;
    private Date modifyTime;
    private Date submitTime;
    private Byte result;
    private String opinion;
    private Integer approveId;
    private Date approveTime;

}