package com.ttt.mim.domain;

import lombok.*;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class IndentOutData {
    private Integer id;
    private String indentNum;
    private String indentName;
    private Integer creatorId;
    private String creatorName;
    private Integer customerId;
    private String customerName;
    private Double costMoney;
    private Double quoteMoney;
    private Date createTime;
    private Date modifyTime;
}
