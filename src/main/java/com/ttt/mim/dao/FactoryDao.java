package com.ttt.mim.dao;


import com.ttt.mim.domain.Factory;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface FactoryDao {
    /**
     * 添加厂家信息
     * @param factory 厂家实体
     * @return 添加个数
     */
    int add(Factory factory);

    /**
     * 修改厂家信息
     * @param factory 厂家实体
     * @return 修改个数
     */
    int update(Factory factory);

    /**
     * 删除厂家信息
     * @param id 目标厂家id
     * @return 删除个数
     */
    int delete(Integer id);

    /**
     * 批量删除厂家信息
     * @param ids 目标厂家集合
     * @return 删除个数
     */
    int batchDelete(Integer[] ids);

    /**
     * 查询所有厂家信息
     * @param params 多查询条件
     * @return list数组
     */
    List<Factory> query(Map<String, Object> params);

    /**
     * 根据id查询厂家信息
     * @param id 厂家编号
     * @return 厂家信息
     */
    Factory findById(Integer id);

    /**
     * 查询总记录
     * @return 总记录条数
     */
    int count();
}
