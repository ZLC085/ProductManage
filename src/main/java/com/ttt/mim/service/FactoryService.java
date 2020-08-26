package com.ttt.mim.service;
import com.ttt.mim.domain.Factory;
import java.util.List;
import java.util.Map;

public interface FactoryService {
    /**
     * 查询厂家列表
     * @param params 查询条件
     * @return 厂家列表
     */
    List<Factory> query(Map<String, Object> params);

    /**
     * 添加厂家
     * @param factory 厂家信息
     * @return  添加条数
     */
    int add(Factory factory);

    /**
     * 修改厂家信息
     * @param factory 厂家信息
     * @return 修改条数
     */
    int update(Factory factory);

    /**
     * 单个删除厂家
     * @param id 厂家编号
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 批量删除厂家
     * @param ids 厂家编号数组
     * @return 删除条数
     */
    int batchDelete(Integer[] ids);

    /**
     * 根据id查询厂家
     * @param id 厂家编号
     * @return 厂家信息
     */
    Factory findById(Integer id);
    /**
     * 查询总记录
     * @return 记录数
     */
    int count();
}
