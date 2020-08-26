package com.ttt.mim.dao;
import com.ttt.mim.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户管理
 */
@Repository
public interface UserDao {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户实体
     */
    User findByUsername(String username);

    /**
     * 根据用户id查询用户名
     * @param id 用户id
     * @return 用户名
     */
    String getUsername(int id);

    /**
     * 通过id获取y用户信息
     * @param id 用户id
     * @return 用户实体
     */
    User getById(Integer id);

    /**
     * 查询用户列表
     * @param params 参数集合
     * @return 用户列表
     */
    List<User> query(Map<String, Object> params);

    /**
     * 添加用户
     * @param user 用户实体
     * @return 添加个数
     */
    int add(User user);

    /**
     * 修改用户
     * @param user 用户实体
     * @return 修改个数
     */
    int update(User user);

    /**
     * 删除单个用户
     * @param id 用户Id
     * @return 删除个数
     */
    int delete(Integer id);

    /**
     * 批量删除用户
     * @param ids 用户Id数组
     * @return 删除个数
     */
    int batchDelete(Integer[] ids);

    /**
     * 查询总记录
     * @return 总记录条数
     */
    int count();

}
