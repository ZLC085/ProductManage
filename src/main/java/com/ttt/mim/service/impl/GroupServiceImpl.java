package com.ttt.mim.service.impl;

import com.ttt.mim.dao.GroupDao;
import com.ttt.mim.domain.Group;
import com.ttt.mim.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class GroupServiceImpl implements GroupService {
    private final GroupDao groupDao;
    @Autowired
    public GroupServiceImpl(GroupDao groupDao){this.groupDao=groupDao;}

    /**
     * 添加用户组
     * @param group 用户组实体
     * @return 添加条数
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Group group) {
        group.setCreateTime(new Date());
        group.setModifyTime(new Date());
        return groupDao.add(group);
    }

    /**
     * 删除用户组
     * @param ids 用户组ID
     * @return 删除条数
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer[] ids) {
        return groupDao.delete(ids);
    }

    /**
     * 修改用户组
     * @param group 用户组实体
     * @return 修改条数
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(Group group) {
        group.setModifyTime(new Date());
        return groupDao.update(group);
    }

    /**
     * 多条件查询用户组
     * @param params 查询参数集
     * @return 符合条件的用户组集
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Group> query(Map<String, Object> params) {
        return groupDao.query(params);
    }
}
