package com.ttt.mim.service;

import com.ttt.mim.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 验证密码
     * @param username 用户名
     * @return 密码是否正确
     */
    User checkLogin(String username, String password);

    /**
     * 验证修改信息
     * @param user 用户实体
     * @return 是否可以修改
     */
    int checkUpdate(User user);

    /**
     * 通过用户id查询用户名
     * @param id 用户id
     * @return 用户名
     */
    String getUsername(Integer id);

    /**
     * 通过用户id查询用户信息
     * @param id 用户id
     * @return 用户实体
     */
    User getById(Integer id);

    /**
     * 查询用户列表
     * @param params 查询条件
     * @return 用户列表
     */
    List<User> query(Map<String, Object> params);

    /**
     * 添加用户
     * @param user 实体对象
     * @return 记录数
     */
    int add(User user);

    /**
     * 修改用户
     * @param user 实体对象
     * @return 记录数
     */
    int update(User user);

    /**
     * 删除单个用户
     * @param id 用户id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 批量删除用户
     * @param ids 用户id数组
     * @return 删除条数
     */
    int batchDelete(Integer[] ids);

    /**
     * 查询总记录
     * @return 记录数
     */
    int count();

}
