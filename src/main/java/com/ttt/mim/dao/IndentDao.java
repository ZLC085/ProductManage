package com.ttt.mim.dao;

import com.ttt.mim.domain.Indent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 订单管理 Dao
 * @author Abor
 */
@Repository
public interface IndentDao {
    /**
     * 订单添加
     * @param indent 订单对象（注：对象中的属性[id]不会写入到数据表中）
     * @return 数据表（indent）受影响的行数
     */
    int add(Indent indent);

    /**
     * 订单修改
     * @param indent 订单对象（注：只会更新[name,remark,modifyTime]字段）
     * @return 数据表（indent）受影响的行数
     */
    int update(Indent indent);

    /**
     * 订单删除
     * @param indentNum 订单编号（注：是[num]字段，非[id]字段）
     * @return 数据表（indent）受影响的行数
     */
    int delete(String indentNum);

    /**
     * 批量删除订单
     * @param indentNums 订单编号数组（注：是[num]字段，非[id]字段）
     * @return 数据表（indent）受影响的行数
     */
    int batchDelete(String[] indentNums);

    /**
     * 通过订单编号获取订单
     * @param indentNum 订单编号（注：是[num]字段，非[id]字段）
     * @return 订单对象
     */
    Indent get(String indentNum);

    /**
     * 通过组合条件查询订单
     * @param conditions 组合条件map（可选条件：Indent类的所有属性以及[startTime,endTime]）
     * @return 订单对象列表
     */
    List<Indent> query(Map<String, Object> conditions);

    /**
     * 获取今天最后订单编号
     * @param todayNumStr 编号字符串（今天）
     * @return 订单编号
     */
    String getTodayMaxIndentNum(String todayNumStr);
}
