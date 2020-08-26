package com.ttt.mim.service.impl;

import com.ttt.mim.dao.PriceDao;
import com.ttt.mim.domain.Price;
import com.ttt.mim.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 业务实现
 * @author sunday
 */
@Service
public class PriceServiceImpl implements PriceService {
    private final PriceDao priceDao;
    @Autowired
    public PriceServiceImpl(PriceDao priceDao){this.priceDao=priceDao;}

    /**
     * 录入物价
     * @param price 物价实体
     * @return 录入条数
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Price price) {
        price.setCreateTime(new Date());
        price.setModifyTime(new Date());
        return priceDao.add(price);
    }

    /**
     * 删除物价就
     * @param id 物价ID
     * @return 删除条数
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer id) {
        return priceDao.delete(id);
    }

    /**
     * 批量删除物价记录
     * @param ids 物价ID集
     * @return 删除条数
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(Integer[] ids) {
        return priceDao.batchDelete(ids);
    }

    /**
     * 根据条件查询物价
     * @param params 查询条件集
     * @return 符合条件的物价列表
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Price> query(Map<String, Object> params) {
        return priceDao.query(params);
    }

    /**
     * 查看物价详情
     * @param id 物价ID
     * @return 物价实体
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Price priceDetail(Integer id) {
        return priceDao.priceDetail(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(Price price) {
        price.setModifyTime(new Date());
        return priceDao.update(price);
    }
}
