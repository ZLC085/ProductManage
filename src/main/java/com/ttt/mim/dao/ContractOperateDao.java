package com.ttt.mim.dao;

import com.ttt.mim.domain.ContractOperate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 合同操作  数据访问
 * @author zl
 * date 2019/8/5 15:00
 */
@Repository
public interface ContractOperateDao {

    /**
     * 根据 id 查询
     * @param id 参数
     * @return 实体对象
     */
    ContractOperate getById(Integer id);

    /**
     * 查询合同回款信息列表
     * @param params 参数集合
     * @return 列表
     */
    List<ContractOperate> query(Map<String, Object> params);

    /**
     * 添加合同
     * @param contractOperate 实体对象
     * @return 添加个数
     */
    int add(ContractOperate contractOperate);

    /**
     * 修改合同
     * @param contractOperate 实体对象
     * @return 修改个数
     */
    int update(ContractOperate contractOperate);

    /**
     * 删除单个合同回款记录
     * @param id 合同id
     * @return 删除个数
     */
    int delete(Integer id);

    /**
     * 批量删除合同回款记录
     * @param ids 合同id数组
     * @return 删除个数
     */
    int batchDelete(Integer[] ids);

}
