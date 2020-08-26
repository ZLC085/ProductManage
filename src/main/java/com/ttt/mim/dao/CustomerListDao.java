package com.ttt.mim.dao;

import com.ttt.mim.domain.Customer;
import com.ttt.mim.domain.CustomerList;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 客户管理
 * @author JY
 */
@Repository
public interface CustomerListDao {

    /**
     *  根据客户id查找客户
     * @param id 客户id
     * @return 客户信息
     */
    CustomerList findById(Integer id);

    /**
     * 客户信息组合查询
     * @param params 组合条件Map
     * @return 客户信息列表
     */
    List<CustomerList> query(Map<String, Object> params);

    /**
     * 组合条件下的数据总条数
     * @param params 组合条件
     * @return 记录数
     */
    int count(Map<String, Object> params);
}
