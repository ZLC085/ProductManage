package com.ttt.mim.service.impl;
import com.ttt.mim.common.exception.BusinessException;
import com.ttt.mim.common.exception.DataAccessException;
import com.ttt.mim.common.exception.DataNotFoundException;
import com.ttt.mim.dao.FactoryDao;
import com.ttt.mim.domain.Factory;
import com.ttt.mim.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class FactoryServiceImpl implements FactoryService{
    private final FactoryDao factoryDao;
    @Autowired
    public FactoryServiceImpl(FactoryDao factoryDao) {
        this.factoryDao= factoryDao;
    }

    @Override
    public List<Factory> query(Map<String, Object> params) { return factoryDao.query(params); }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Factory factory) {
        factory.setCreateTime(new Date());
        factory.setModifyTime(new Date());
        int count = factoryDao.add(factory);
        if (count != 1) {
            throw new BusinessException("添加厂家信息失败:" + factory);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(Factory factory) {
        factory.setModifyTime(new Date());
        int count = factoryDao.update(factory);
        if (count != 1) {
            throw new BusinessException("修改厂家信息失败:" + factory);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer id) {
        int count = factoryDao.delete(id);
        if (count != 1) {
            throw new BusinessException("删除厂家失败", id);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(Integer[] ids) {
        int count = factoryDao.batchDelete(ids);
        if (count != ids.length) {
            throw new DataAccessException("批量删除厂家失败", ids);
        }
        return count;
    }

    @Override
    public Factory findById(Integer id) {
        Factory fac= factoryDao.findById(id);
        if (fac == null){
            throw new DataNotFoundException("厂家不存在：" + id);
        }
        return fac;
    }

    @Override
    public int count() {
        int num = factoryDao.count();
        return num;
    }

}
