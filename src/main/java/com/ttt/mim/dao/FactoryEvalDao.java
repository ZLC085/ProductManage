package com.ttt.mim.dao;

import com.ttt.mim.domain.FactoryEval;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FactoryEvalDao {
    /**
     * 添加对厂家评价
     * @param factoryEval 厂家评价实体
     * @return 添加个数
     */
    int add(FactoryEval factoryEval);

    /**
     * 组合查询该厂家评价
     * @param params 条件
     * @return 厂家评价列表
     */
    List<FactoryEval> query(Map<String, Object> params);

    /**
     * 根据用户id查询评价记录，便于实现仅评价一次
     * @param id 用户id
     * @return 评价信息
     */
    FactoryEval findByUserId(Integer id);
    /**
     * 查询总记录
     * @return 总记录条数
     */
    int count();
}
