package com.ttt.mim.domain;

import com.ttt.mim.common.utils.CheckTools;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class PurchaseDetail {
    private Integer id;
    private String pId;
    private Integer mcId;
    private Integer number;
    private Double sum;

    public PurchaseDetail(Integer mcId, Integer number, Double sum) {
        this.mcId = mcId;
        this.number = number;
        this.sum = sum;
    }
    public Boolean addCheckData(){
        return CheckTools.checkNullOrEmpty(pId,mcId,number,sum);
    }
}
