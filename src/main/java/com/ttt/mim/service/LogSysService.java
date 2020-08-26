package com.ttt.mim.service;

import com.ttt.mim.domain.LogSys;

import java.util.List;
import java.util.Map;

/**
 * @Description 业务方法
 */
public interface LogSysService {
    /**
     * @param logSys 系统日志实体
     * @return 添加条数
     * @Description 添加系统日志
     */
    int add(LogSys logSys);

    /**
     * @param id 系统日志id
     * @return 删除条数
     * @Description 删除单条系统日志
     */
    int delete(Integer id);

    /**
     * @param ids 系统日志id数组
     * @return 删除条数
     * @Description 批量删除系统日志
     */
    int batchDelete(Integer[] ids);

    /**
     * @param params 参数集合
     * @return 系统日志列表
     * @Description 查询系统日志列表
     */
    List<LogSys> query(Map<String, Object> params);

    /**
     * @return 总记录条数
     * @Description 查询总记录条数
     */
    int count();
}
