package com.ttt.mim.service.impl;

import com.ttt.mim.dao.CategoryDao;
import com.ttt.mim.dao.MaterialDao;
import com.ttt.mim.domain.Category;
import com.ttt.mim.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物资分类
 * @author : mao yuhang
 * date : 2019-08-19 17:33
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;
    private final MaterialDao materialDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao, MaterialDao materialDao) {
        this.categoryDao = categoryDao;
        this.materialDao = materialDao;
    }

    @Override
    public List<Category> query(Map<String, Object> params) {
        return categoryDao.query(params);
    }

    @Override
    public Category getById(Integer id) {
        return categoryDao.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Category category) {
        category.setCreate_time(new Date());
        category.setModify_time(new Date());
        // 查询是否存在相同分类名称
        Map<String, Object> params = new HashMap<>();
        params.put("name", category.getName());
        if(!categoryDao.query(params).isEmpty()) {
            return 0;
        }
        return categoryDao.add(category);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(Category category) {
        category.setModify_time(new Date());
        // 查询是否存在相同分类名称
        Map<String, Object> params = new HashMap<>();
        params.put("name", category.getName());
        if(!categoryDao.query(params).isEmpty()) {
            return 0;
        }
        return categoryDao.update(category);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer id) {
        // 查询是否存在子分类
        Map<String, Object> param1 = new HashMap<>();
        param1.put("parent_id", id);
        if (categoryDao.query(param1).isEmpty()) {
            // 查询是否存在物资
            Map<String, Object> param2 = new HashMap<>();
            param2.put("category_id", id);
            if (materialDao.query(param2).isEmpty()) {
                // 不存在则进行删除操作
                return categoryDao.delete(id);
            }
        }
        return 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(Integer[] ids) {
        // 可删除分类的数组
        Integer[] deleteIds = null;/* = new Integer[ids.length];*/

        Map<String, Object> param1;
        Map<String, Object> param2;

        for(int i = 0; i < ids.length; i++) {
            // 查询是否存在子分类
            param1 = new HashMap<>();
            param1.put("parent_id", ids[i]);
            if (categoryDao.query(param1).isEmpty()) {
                // 查询是否存在物资
                param2 = new HashMap<>();
                param2.put("category_id", ids[i]);
                if (materialDao.query(param2).isEmpty()) {
                    // 不存在则添加到可删除的数组里
                    deleteIds[i] = ids[i];
                }
            }
        }
        // 存在可删除的分类则进行删除操作
        if (deleteIds != null) {
            // 批量删除可删除的分类
            return categoryDao.batchDelete(deleteIds);
        }
        return 0;
    }
}
