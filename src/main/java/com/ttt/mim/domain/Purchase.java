package com.ttt.mim.domain;

import com.ttt.mim.common.utils.CheckTools;
import lombok.*;

import java.util.Date;
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Purchase {
    private Integer id;
    private String indentNum;
    private String purchaseNum;
    private String name;
    private String remark;
    private Double sum;
    private Integer factoryId;
    private Date createTime;
    private Date modifyTime;
    private Integer approvalId;
    private Byte result;
    private String opinion;
    private Date approvalTime;

    public Purchase(String indentNum, String name, String remark, Double sum, Integer factoryId, Date createTime, Date modifyTime) {
        this.indentNum = indentNum;
        this.name = name;
        this.remark = remark;
        this.sum = sum;
        this.factoryId = factoryId;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }
    public Boolean addCheckData(){
        return CheckTools.checkNullOrEmpty(purchaseNum,name,sum,factoryId,createTime,modifyTime,result);
    }
}
