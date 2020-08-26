package com.ttt.mim.dao;

import com.ttt.mim.domain.ContractMain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 合同生成  数据访问
 * @author zl
 * date 2019/8/5 15:00
 */
@Repository
public interface ContractMainDao {

    /**
     * 根据 id 查询合同
     * @param id 参数
     * @return 合同 实体
     */
    ContractMain getById(Integer id);

    /**
     * 查询合同列表
     * @param params 参数集合
     * @return 合同列表
     */
    List<ContractMain> query(Map<String, Object> params);

    /**
     * 添加合同
     * @param contractMain 合同实体
     * @return 添加个数
     */
    int add(ContractMain contractMain);

    /**
     * 修改合同
     * @param ContractMain 合同实体
     * @return 修改个数
     */
    int update(ContractMain ContractMain);

    /**
     * 删除单个合同
     * @param id 合同id
     * @return 删除个数
     */
    int delete(Integer id);

    /**
     * 批量删除合同
     * @param ids 合同id数组
     * @return 删除个数
     */
    int batchDelete(Integer[] ids);

    /**
     * 查询总记录
     * @return 总记录条数
     */
    int count();


}
