package com.ttt.mim.domain;

import com.ttt.mim.common.utils.CheckTools;
import lombok.*;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Quote {
    private Integer id;
    private String indentNum;
    private Integer mId;
    private Integer mcId;
    private Double quote;
    private Integer number;
    private Double sum;
    private Date createTime;

    public Boolean addCheckData(){
        return CheckTools.checkNullOrEmpty(indentNum,mId,mcId,quote,number,sum,createTime);
    }
}
