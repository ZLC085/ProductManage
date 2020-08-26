package com.ttt.mim.service;

import com.ttt.mim.domain.Material;

import java.util.List;
import java.util.Map;

/**
 * 物资
 * @author : mao yuhang
 * date : 2019-08-19 17:29
 */
public interface MaterialService {
    /**
     * 添加物资
     * @param material 物资信息
     * @return 物资实体
     */
    int add(Material material);

    /**
     * 修改物资
     * @param material 物资信息
     * @return 物资实体
     */
    int update(Material material);

    /**
     * 删除物资
     * @param id 物资id
     * @return 成功条数
     */
    int delete(Integer id);

    /**
     * 批量删除
     * @param ids 物资id
     * @return 成功条数
     */
    int batchDelete(Integer[] ids);

    /**
     * 通过编号查询物资
     * @param id 编号
     * @return 物资实体
     */
    Material getById(Integer id);

    /**
     * 按条件查询物资
     * @param params 查询条件
     * @return 物资实体列表
     */
    List<Material> query(Map<String, Object> params);
}
