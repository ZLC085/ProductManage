package com.ttt.mim.dao;

import com.ttt.mim.domain.MenuInGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户组关联权限菜单Dao
 * @author sunday
 */
@Repository
public interface G2MDao {
    /**
     * 添加权限菜单到用户组
     * @param menuIds 菜单ID
     * @param groupId 用户组ID
     * @return 添加条数
     */
    int addm2g(Integer[] menuIds, Integer groupId);

    /**
     * 从用户组移除权限菜单
     * @param ids 主键ID
     * @return 移除条数
     */
    int delm2g(Integer[] ids);

    /**
     * 根据用户组ID查询菜单
     * @param groupId 用户组ID
     * @return 菜单列表
     */
    List<MenuInGroup> queryMbyGid(Integer groupId);
}
