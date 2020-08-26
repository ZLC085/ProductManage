package com.ttt.mim.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class IndentDetailData {
    private String indentNum;
    private Double quote;
    private Integer number;
    private Double sum;
    private String factoryName;
    private Double cost;
    private String mName;
    private String spec;
    private Integer picId;
}
