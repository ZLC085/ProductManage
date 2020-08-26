package com.ttt.mim.dao;

import com.ttt.mim.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 声明Customer增删改查的方法
 * @author JY
 */
@Repository
public interface CustomerDao {
    /**
     * 添加客户信息
     * @param customer 添加的客户
     * @return 数据库中添加的行数
     */
    int add(Customer customer);

    /**
     * 修改客户信息
     * @param customer 修改的客户
     * @return 数据库中修改的行数
     */
    int update(Customer customer);

    /**
     * 根据客户id删除客户在数据库中的信息
     * @param id 客户在数据库中的id
     * @return 数据库中删除的行数
     */
    int delete(Integer id);

    /**
     * 根据客户id批量删除客户信息
     * @param ids 多个客户在数据库中的id
     * @return 数据库中删除的行数数组
     */
    int batchDelete(Integer[] ids);

    /**
     * 根据id查找客户
     * @param id 客户id
     * @return customer
     */
    Customer findById(Integer id);

    /**
     * 根据客户名称查看信息详情
     * @param name 客户名称
     * @return 客户信息详情
     */
    Customer findByName(String name);

    /**
     * 根据组合条件查找客户信息
     * @param params 组合条件Map
     * @return 客户信息列表
     */
    List<Customer> query(Map<String, Object> params);
}
