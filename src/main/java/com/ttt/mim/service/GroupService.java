package com.ttt.mim.service;

import com.ttt.mim.domain.Group;

import java.util.List;
import java.util.Map;

/**
 * 业务方法
 */
public interface GroupService {
    /**
     * 添加用户组
     * @param group 用户组实体
     * @return 添加个数
     */
    int add(Group group);

    /**
     * 删除用户组
     * @param id 用户组ID
     * @return 删除个数
     */
    int delete(Integer[] ids);

    /**
     * 修改用户组
     * @param group 用户组实体
     * @return 修改个数
     */
    int update(Group group);

    /**
     * 组合条件查询用户组
     * @param params 查询参数集
     * @return 符合条件的用户组列表
     */
    List<Group> query(Map<String, Object> params);
}
