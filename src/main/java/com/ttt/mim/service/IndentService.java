package com.ttt.mim.service;

import com.alibaba.fastjson.JSONObject;
import com.ttt.mim.domain.Indent;
import com.ttt.mim.domain.IndentOutData;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 订单管理
 * @author Abor
 */
public interface IndentService {
    /**
     * 添加订单
     * @param data json对象
     *             数据格式：{
     *             indent:{},
     *             quotes:[{},{},{}...]
     *             }
     * @return 数据表受影响行数
     */
    int add(JSONObject data);
    /**
     * 订单修改
     * @param indent 订单对象（注：只会更新[name,remark,modifyTime]字段）
     * @return 数据表受影响行数
     */
    int update(Indent indent);

    /**
     * 根据订单编号删除订单
     * @param indentNum 订单编号
     * @return 数据表受影响行数
     */
    int delete(String indentNum);

    /**
     * 批量删除订单
     * @param indentNums 订单编号数组
     * @return 数据表受影响行数
     */
    int batchDelete(String[] indentNums);

    Map<String, byte[]> getAllDocx(HttpServletRequest request, String[] indentNums);
    Map<String, byte[]> getAllExcel(HttpServletRequest request, String[] indentNums) throws IOException;

    /**
     * 根据订单编号获取订单
     * @param indentNum 订单编号
     * @return 订单对象
     */
    Indent get(String indentNum);

    /**
     * 根据组合条件查询订单数据
     * @param map 组合条件
     * @return 订单输出数据列表
     */
    List<IndentOutData> query(Map<String,Object> map);
    int count(Map<String,Object> map);

    /**
     * 根据订单编号查询订单详情
     * @param indentNum 订单编号
     * @return 包含订单所有信息的json对象
     */
    JSONObject getDetails(String indentNum);

    /**
     * 获取当前可用订单编号
     * @return 订单编号
     */
    String getAvailableIndentNum();
    /**
     * 获取订单是否可修改
     * @param indentNum 订单编号
     * @return 可修改返回[true]，否则返回[false]
     */
    Boolean isRevisable(String indentNum);

    ResponseEntity<byte[]> getResponseEntity(HttpServletRequest request, Map<String,byte[]> map) throws IOException;
}
