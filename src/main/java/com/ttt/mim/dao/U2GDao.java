package com.ttt.mim.dao;

import com.ttt.mim.domain.UserInGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户组关联用户Dao
 * @author sunday
 */
@Repository
public interface U2GDao {
    /**
     * 添加用户到用户组
     *
     * @param userIds 用户ID集
     * @param groupId 用户组ID
     * @return 添加个数
     */
    int addu2g(Integer[] userIds, Integer groupId);

    /**
     * 从用户组移除用户
     *
     * @param userIds 用户ID集
     * @param groupId 用户组ID
     * @return 移除个数
     */
    int delu2g(Integer[] userIds, Integer groupId);

    /**
     * 根据用户组ID查询用户
     * @param groupId 用户组ID
     * @return 用户列表
     */
    List<UserInGroup> queryUbyGid(Integer groupId);
}
