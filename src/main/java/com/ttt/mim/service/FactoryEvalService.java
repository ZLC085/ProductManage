package com.ttt.mim.service;

import com.ttt.mim.domain.FactoryEval;

import java.util.List;
import java.util.Map;

public interface FactoryEvalService {
    /**
     * 组合条件查询厂家的评价
     * @param params 相关条件
     * @return 评价列表
     */
    List<FactoryEval> query(Map<String, Object> params);


    /**
     * 添加厂家评价
     * @param factoryEval 评价信息
     * @return 添加条数
     */
    int add(FactoryEval factoryEval);

    /**
     * 根据用户id查询评价信息，便于查重
     * @param id 用户id
     * @return  评价信息
     */
    FactoryEval findByUserId(Integer id);
    /**
     * 查询总记录
     * @return 记录数
     */
    int count();
}
