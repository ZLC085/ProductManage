package com.ttt.mim.dao;

import com.ttt.mim.domain.PurchaseDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 采购单详情
 * @author Abor 
 */
@Repository
public interface PurchaseDetailDao {
    /**
     * 采购单详情添加
     * @param purchaseDetail 采购单详情对象（注：属性[id]不会用到）
     * @return 数据表（purchase_detail）表受影响行数
     */
    int add(PurchaseDetail purchaseDetail);

    /**
     * 批量添加采购单详情
     * @param purchaseDetailList 采购单详情列表
     * @return 数据表受影响行数
     */
    int batchAdd(List<PurchaseDetail> purchaseDetailList);
    /**
     * 采购单详情删除
     * @param purchaseDetailId 采购单详情编号（注：对应[id]属性）
     * @return 数据表（purchase_detail）表受影响行数
     */
    int delete(Integer purchaseDetailId);

    /**
     * 采购单详情批量删除
     * @param purchaseDetailIds 采购单详情编号数组（注：对应[id]属性）
     * @return 数据表（purchase_detail）表受影响行数
     */
    int batchDelete(Integer[] purchaseDetailIds);

    /**
     * 根据采购单编号删除采购单细节
     * @param purchaseNum 采购单编号
     * @return 数据表受影响行数
     */
    int deleteByPurchaseNum(String purchaseNum);

    /**
     * 根据组合条件查询采购单详情列表
     * @param conditions 组合条件map（注：条件可以包含PurchaseDetail类所有属性）
     * @return 采购单详情列表
     */
    List<PurchaseDetail> query(Map<String, Object> conditions);

    /**
     * 根据采购单详情编号查询采购单详情
     * @param purchaseDetailId 采购单详情编号
     * @return 采购单详情对象
     */
    PurchaseDetail get(Integer purchaseDetailId);
}
