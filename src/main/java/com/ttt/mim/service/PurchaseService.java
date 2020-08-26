package com.ttt.mim.service;

import com.alibaba.fastjson.JSONObject;
import com.ttt.mim.domain.Purchase;
import com.ttt.mim.domain.PurchaseDetail;
import com.ttt.mim.domain.PurchaseOutData;

import java.util.List;
import java.util.Map;

public interface PurchaseService {
    /**
     * 采购单添加
     * @param jsonObject json数据{
     *                   purchases:[{},{},{}...]
     *                   purchaseDetails:[[{},{}...],[{},{}...],[{},{}...]]
     * }
     *
     * @return 数据表受影响行数
     */
    int add(JSONObject jsonObject);

    int add( Map<Purchase, List<PurchaseDetail>> map);
    /**
     * 采购单更新
     * @param jsonObject json数据
     * @return 数据表受影响行数
     */
    int update(JSONObject jsonObject);

    /**
     * 采购单删除
     * @param purchaseNum 采购单编号
     * @return 数据表受影响行数
     */
    int delete(String purchaseNum);

    /**
     * 批量删除采购单
     * @param purchaseNums 采购单编号数组
     * @return 数据表受影响行数
     */
    int batchDelete(String[] purchaseNums);

    /**
     * 根据订单编号删除采购单
     * @param indentNum 订单编号
     * @return 数据表受影响行数
     */
    int deleteByIndentNum(String indentNum);

    /**
     * 采购单审批
     * @param purchase 采购单对象
     * @return 数据表受影响行数
     */
    int approve(Purchase purchase);

    /**
     * 根据采购单编号获取采购单
     * @param purchaseNum 采购单编号
     * @return 采购单对象
     */
    Purchase get(String purchaseNum);

    /**
     * 根据组合条件查询采购单
     * @param map 组合条件map
     * @return 采购单输出数据列表
     */
    List<PurchaseOutData> query(Map<String,Object> map);
    int count(Map<String,Object> map);

    /**
     * 获取采购单细节
     * @param purchaseNum 采购单编号
     * @return json数据类型
     */
    JSONObject getDetails(String purchaseNum);

    /**
     * 采购单是否可编辑
     * @param purchaseNum 采购单编号
     * @return 可编辑返回true,否则返回false
     */
    Boolean isRevisable(String purchaseNum);
    /**
     * 获取当前可用采购单编号
     * @return 采购单编号
     */
    String getAvailablePurchaseNum();

    /**
     * 将json数据转化为可直接添加修改的实体类型
     * @param jsonObject json数据
     * @return 实体类型map
     */
    Map<Purchase,List<PurchaseDetail>> convertJsonData(JSONObject jsonObject);

}
