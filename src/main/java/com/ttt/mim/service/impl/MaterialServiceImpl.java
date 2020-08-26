package com.ttt.mim.service.impl;

import com.ttt.mim.dao.MaterialDao;
import com.ttt.mim.domain.Material;
import com.ttt.mim.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 物资
 * @author : mao yuhang
 * date : 2019-08-19 17:57
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialDao materialDao;

    @Autowired
    public MaterialServiceImpl(MaterialDao materialDao) {
        this.materialDao = materialDao;
    }

    @Override
    public List<Material> query(Map<String, Object> params) {
        return materialDao.query(params);
    }

    @Override
    public Material getById(Integer id) {
        return materialDao.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Material material) {
        material.setCreate_time(new Date());
        material.setModify_time(new Date());
        return materialDao.add(material);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(Material material) {
        material.setModify_time(new Date());
        return materialDao.update(material);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer id) {
        return materialDao.delete(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(Integer[] ids) {
        return materialDao.batchDelete(ids);
    }
}
