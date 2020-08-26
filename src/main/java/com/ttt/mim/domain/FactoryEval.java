package com.ttt.mim.domain;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
public class FactoryEval {
    @Getter @Setter private Integer id;
    @Getter @Setter private Integer factoryId;
    @Getter @Setter private Integer UserId;
    @Getter @Setter private  Float score;
    @Getter @Setter private String content;
    @Getter @Setter private Date createTime;
    @Getter @Setter private String userName;
}
