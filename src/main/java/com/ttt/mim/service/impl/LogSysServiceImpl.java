package com.ttt.mim.service.impl;

import com.ttt.mim.dao.LogSysDao;
import com.ttt.mim.domain.LogSys;
import com.ttt.mim.service.LogSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description 业务方法
 */
@Service
public class LogSysServiceImpl implements LogSysService {

    private LogSysDao logSysDao;

    @Autowired
    public LogSysServiceImpl(LogSysDao logSysDao) {
        this.logSysDao = logSysDao;
    }

    /**
     * @param logSys 系统日志实体
     * @return 添加条数
     * @Description 添加系统日志
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(LogSys logSys) {
        return logSysDao.add(logSys);
    }

    /**
     * @param id 系统日志id
     * @return 删除条数
     * @Description 删除单条系统日志
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer id) {
        return logSysDao.delete(id);
    }

    /**
     * @param ids 系统日志id数组
     * @return 删除条数
     * @Description 批量删除系统日志
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(Integer[] ids) {
        return logSysDao.batchDelete(ids);
    }

    /**
     * @param params 参数集合
     * @return 系统日志列表
     * @Description 查询系统日志列表
     */
    @Override
    public List<LogSys> query(Map<String, Object> params) {
        return logSysDao.query(params);
    }

    /**
     * @return 总记录条数
     * @Description 查询总记录条数
     */
    @Override
    public int count() {
        return logSysDao.count();
    }
}
