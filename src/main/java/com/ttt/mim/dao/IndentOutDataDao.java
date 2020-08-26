package com.ttt.mim.dao;

import com.ttt.mim.domain.IndentOutData;

import java.util.List;
import java.util.Map;

public interface IndentOutDataDao {

    /**
     * 根据订单编号查找订单输出类型
     * @param indentNum 订单编号
     * @return 订单输出类型
     */
    IndentOutData get(String indentNum);

    /**
     * 订单输出类型组合查询
     * @param params 组合条件Map
     * @return 订单输出类型列表
     */
    List<IndentOutData> query(Map<String, Object> params);

    /**
     * 组合条件下的数据总条数
     * @param params 组合条件
     * @return 记录数
     */
    int count(Map<String,Object> params);
}
