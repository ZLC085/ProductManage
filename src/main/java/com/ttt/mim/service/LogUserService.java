package com.ttt.mim.service;

import com.ttt.mim.domain.LogUser;

import java.util.List;
import java.util.Map;

/**
 * @Description 业务方法
 */
public interface LogUserService {
    /**
     * @param logUser 用户日志实体
     * @return 添加条数
     * @Description 添加用户日志
     */
    int add(LogUser logUser);

    /**
     * @param id 用户日志id
     * @return 删除条数
     * @Description 删除单条用户日志
     */
    int delete(Integer id);

    /**
     * @param ids 用户日志id数组
     * @return 删除条数
     * @Description 批量删除用户日志
     */
    int batchDelete(Integer[] ids);

    /**
     * @param params 参数集合
     * @return 用户日志列表
     * @Description 查询用户日志列表
     */
    List<LogUser> query(Map<String, Object> params);

    /**
     * @return 总记录条数
     * @Description 查询总记录条数
     */
    int count();
}
