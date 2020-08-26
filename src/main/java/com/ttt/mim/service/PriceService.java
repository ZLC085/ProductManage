package com.ttt.mim.service;

import com.ttt.mim.domain.Price;

import java.util.List;
import java.util.Map;
/**
 * 业务方法
 */
public interface PriceService {
    /**
     * 录入物价
     * @param price 物价实体
     * @return 录入条数
     */
    int add(Price price);

    /**
     * 删除物价记录
     * @param id 物价ID
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 批量删除物价
     * @param ids 物价ID集
     * @return 删除条数
     */
    int batchDelete(Integer[] ids);

    /**
     * 组合条件查询物价
     * @param params 查询条件集
     * @return 符合条件的物价列表
     */
    List<Price> query(Map<String, Object> params);

    /**
     * 根据ID查看物价详情
     * @param id 物价ID
     * @return 物价实体
     */
    Price priceDetail(Integer id);

    /**
     * 修改物价
     * @param price 物价实体
     * @return 修改条数
     */
    int update(Price price);
}
