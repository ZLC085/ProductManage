package com.ttt.mim.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class PurchaseMaterials {
    private String purchaseNum;
    private Integer number;
    private Double sum;
    private String name;
    private String spec;
    private String unit;
    private String use;
    private String useUnit;
    private Integer picId;
    private Double cost;
}
