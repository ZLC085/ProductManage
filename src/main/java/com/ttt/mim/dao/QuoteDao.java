package com.ttt.mim.dao;

import com.ttt.mim.domain.Quote;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 报价管理
 */
@Repository
public interface QuoteDao {
    /**
     * 报价添加
     * @param quote  报价对象（注：属性[id]不会用到）
     * @return 数据表（m_quote）受影响行数
     */
    int add(Quote quote);

    /**
     * 批量添加
     * @param quotes 报价列表
     * @return 数据表受影响行数
     */
    int batchAdd(List<Quote> quotes);
    /**
     * 根据报价编号删除报价
     * @param quoteId 报价编号（注：对应[id]属性）
     * @return 数据表（m_quote）受影响行数
     */
    int delete(Integer quoteId);

    /**
     * 根据报价数组编号批量删除报价
     * @param quoteIds 报价编号数组
     * @return 数据表（m_quote）受影响行数
     */
    int batchDelete(Integer[] quoteIds);

    /**
     * 根据订单编号删除报价
     * @param indentNum 订单编号
     * @return 数据表（m_quote）受影响行数
     */
    int deleteByIndentNum(String indentNum);

    /**
     * 根据组合条件查询报价
     * @param conditions 组合条件map（注：组合条件包括Quote所有属性以及[startTime,endTime]）
     * @return 符合条件的报价列表
     */
    List<Quote> query(Map<String, Object> conditions);

    /**
     *根据报价编号查询报价
     * @param quoteId 报价编号（注：对应[id]属性）
     * @return 报价对象
     */
    Quote get(Integer quoteId);
}
