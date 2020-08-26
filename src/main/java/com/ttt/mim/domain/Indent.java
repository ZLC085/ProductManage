package com.ttt.mim.domain;

import com.ttt.mim.common.utils.CheckTools;
import lombok.*;

import javax.ws.rs.PUT;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Indent {
    /**
     * 订单表主键id
     */
    private Integer id;
    /**
     * 订单编号
     */
    private String num;
    /**
     * 订单名称
     */
    private String name;
    /**
     * 创建此订单的用户编号
     */
    private Integer userId;
    /**
     * 客户编号
     */
    private Integer customerId;
    /**
     * 订单成本价
     */
    private Double costMoney;
    /**
     * 订单总报价
     */
    private Double quoteMoney;
    /**
     * 订单备注
     */
    private String remark;
    /**
     * 订单创建时间
     */
    private Date createTime;
    /**
     * 订单修改时间
     */
    private Date modifyTime;

    /**
     * 非全参构造器，供修改使用
     * @param num 订单编号
     * @param name 订单名称
     * @param remark 订单备注
     * @param modifyTime 订单修改时间
     */
    public Indent(String num, String name, String remark, Date modifyTime) {
        this.num = num;
        this.name = name;
        this.remark = remark;
        this.modifyTime = modifyTime;
    }
    public Boolean addCheckData(){
        return CheckTools.checkNullOrEmpty(num,name,userId,customerId,costMoney,quoteMoney,createTime,modifyTime);
    }
    public Boolean updateCheckData(){
        return CheckTools.checkNullOrEmpty(num,name,modifyTime);
    }
}
