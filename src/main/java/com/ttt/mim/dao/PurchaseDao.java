package com.ttt.mim.dao;

import com.ttt.mim.domain.Purchase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 采购单管理 Dao
 * @author Abor
 */
@Repository
public interface PurchaseDao {
    /**
     * 采购单添加
     * @param purchase 采购单对象（注：属性[id,approvalId,result,opinion,approvalTime]不会用到）
     * @return 数据表（purchase）受影响行数
     */
    int add(Purchase purchase);

    /**
     * 采购单修改
     * @param purchase 采购单对象（注：只会更新[name,remark,result,modifyTime]属性,通过[purchaseNum]属性更新）
     * @return 数据表（purchase）受影响行数
     */
    int update(Purchase purchase);

    /**
     * 采购单删除
     * @param purchaseNum 采购单编号（注：是[purchaseNum]属性，非[id]属性）
     * @return 数据表（purchase）受影响行数
     */
    int delete(String purchaseNum);

    /**
     * 采购单批量删除
     * @param purchaseNums 采购单编号数组（注：是[purchaseNum]属性，非[id]属性）
     * @return 数据表（purchase）受影响行数
     */
    int batchDelete(String[] purchaseNums);

    /**
     * 根据订单编号删除采购单
     * @param indentNum 订单编号
     * @return 数据表（purchase）受影响行数
     */
    int deleteByIndentNum(String indentNum);

    /**
     * 根据组合条件查询采购单
     * @param conditions 组合条件map（可选条件：Purchase类的所有属性以及[startTime,endTime,approvalStartTime,approvalEndTime]）
     * @return 采购单对象列表
     */
    List<Purchase> query(Map<String, Object> conditions);

    /**
     * 根据采购单编号获取采购单
     * @param purchaseNum 采购单编号（注：是[purchaseNum]属性，非[id]属性）
     * @return 采购单对象
     */
    Purchase get(String purchaseNum);

    /**
     * 采购单审批
     * @param purchase 采购单对象（注：只会更新[approvalId,result,opinion,approvalTime]属性,通过[purchaseNum]属性更新）
     * @return 数据表（purchase）受影响行数
     */
    int approve(Purchase purchase);

    /**
     * 根据订单编号获取订单下的采购单审批情况
     * @param indentNum 订单编号
     * @return 审批状态数组
     */
    List<Byte> getResultsByIndentNum(String indentNum);

    /**
     * 根据采购单编号获取审批结果
     * @param purchaseNum  采购单编号
     * @return 审批结果
     */
    Byte getResultByPurchaseNum(String purchaseNum);

    /**
     * 获取今天最大采购单编号
     * @return 采购单编号，若今天无采购单，则返回null
     */
    String getTodayMaxPurchaseNum(String todayNumStr);

    /**
     * 根据订单编号获取采购单编号列表
     * @param indentNum 订单编号
     * @return 采购单编号列表
     */
    List<String> getPurchaseNumsByIndentNum(String indentNum);
}
