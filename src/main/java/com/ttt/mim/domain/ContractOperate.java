package com.ttt.mim.domain;

import lombok.Data;

import java.util.Date;

/**
 * 合同操作  实体类
 * @author zl
 * date 2019/8/5 15:00
 */
@Data
public class ContractOperate {
    private Integer id;
    private Integer contractId;
    private Double payback;
    private Date payTime;
    private Integer customerId;
    private String remark;

    private String checkFilename;
    private byte[] checkFile;
    private Long checkFilesize;

    private Date createTime;
    private Date modifyTime;

}