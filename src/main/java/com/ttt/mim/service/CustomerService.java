package com.ttt.mim.service;

import com.ttt.mim.domain.Customer;
import com.ttt.mim.domain.CustomerList;

import java.util.List;
import java.util.Map;

/**
 * 客户管理
 * @author JY
 */
public interface CustomerService {

    /**
     * 添加
     * @param customer 实体对象
     * @return 受影响行数
     */
    int add(Customer customer);

    /**
     * 修改
     * @param customer 实体对象
     * @return 受影响行数
     */
    int update(Customer customer);

    /**
     * 删除
     * @param id 需要删除的客户id
     * @return 受影响行数
     */
    int delete(Integer id);

    /**
     * 批量删除
     * @param ids 需要删除的客户id数组
     * @return 受影响行数
     */
    int batchDelete(Integer[] ids);

    /**
     * 根据客户id查找
     * @param id 客户id
     * @return customer
     */
    Customer findById(Integer id);

    /**
     * 根据客户名称查看信息详情
     * @param name 需要查看信息详情的客户名称
     * @return 客户信息详情
     */
    Customer findByName(String name);

    /**
     * 根据组合条件查询
     * @param params 组合条件
     * @return 客户信息列表
     */
    List<CustomerList> query(Map<String, Object> params);
    int count(Map<String, Object> params);
}
