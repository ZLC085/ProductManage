package com.ttt.mim.domain;

import lombok.*;

import java.util.Date;

/**
 * 客户管理
 * @author JY
 */
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Customer {

        /**
         * 客户编号id
         */
        private Integer id;

        /**
         * 客户企业名称
         */
        private String name;

        /**
         * 客户企业曾用名
         */
        private String formerName;

        /**
         * 客户企业联系方式
         */
        private String phone;

        /**
         * 统一社会信用代码
         */
        private String socialCode;

        /**
         * 企业法定代表人
         */
        private String legalPerson;

        /**
         * 企业运营状态，如续存
         */
        private String status;

        /**
         * 所属行业，如互联网
         */
        private String industry;

        /**
         * 企业允许运营开始时间
         */
        private Date runStart;

        /**
         * 企业允许运营结束时间
         */
        private Date runEnd;

        /**
         * 企业所在地址
         */
        private String address;

        /**
         * 客户企业与用户的交易信用情况
         */
        private String credit;

        /**
         *  客户信息创建时间
         */
        private Date createTime;

        /**
         *  客户信息修改时间
         */
        private Date modifyTime;
}
