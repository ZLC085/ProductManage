package com.ttt.mim.service.impl;

import com.ttt.mim.dao.LogUserDao;
import com.ttt.mim.domain.LogUser;
import com.ttt.mim.service.LogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description 业务方法
 */
@Service
public class LogUserServiceImpl implements LogUserService {

    private final LogUserDao logUserDao;

    @Autowired
    public LogUserServiceImpl(LogUserDao logUserDao) {
        this.logUserDao = logUserDao;
    }

    /**
     * @param logUser 用户日志实体
     * @return 添加条数
     * @Description 添加用户日志
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(LogUser logUser) {
        return logUserDao.add(logUser);
    }

    /**
     * @param id 用户日志id
     * @return 删除条数
     * @Description 删除单条用户日志
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer id) {
        return logUserDao.delete(id);
    }

    /**
     * @param ids 用户日志id数组
     * @return 删除条数
     * @Description 批量删除用户日志
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(Integer[] ids) {
        return logUserDao.batchDelete(ids);
    }

    /**
     * @param params 参数集合
     * @return 用户日志列表
     * @Description 查询用户日志列表
     */
    @Override
    public List<LogUser> query(Map<String, Object> params) {
        return logUserDao.query(params);
    }

    /**
     * @return 总记录条数
     * @Description 查询总记录条数
     */
    @Override
    public int count() {
        return logUserDao.count();
    }
}
