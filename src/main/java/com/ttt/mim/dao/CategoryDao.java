package com.ttt.mim.dao;

import com.ttt.mim.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 物资分类
 * @author : mao yuhang
 * date : 2019-08-19 16:58
 */
@Repository
public interface CategoryDao {
    /**
     * 添加分类
     * @param category 分类信息
     * @return 分类实体
     */
    int add(Category category);

    /**
     * 修改分类
     * @param category 分类信息
     * @return 分类实体
     */
    int update(Category category);

    /**
     * 删除分类
     * @param id 分类id
     * @return 成功条数
     */
    int delete(Integer id);

    /**
     * 批量删除
     * @param ids 分类id
     * @return 成功条数
     */
    int batchDelete(Integer[] ids);

    /**
     * 通过编号查询分类
     * @param id 编号
     * @return 分类实体
     */
    Category getById(Integer id);

    /**
     * 按条件查询分类
     * @param params 条件
     * @return 分类实体列表
     */
    List<Category> query(Map<String, Object> params);
}
