package com.ttt.mim.service.impl;
import com.ttt.mim.common.exception.BusinessException;
import com.ttt.mim.common.exception.DataNotFoundException;
import com.ttt.mim.dao.FactoryEvalDao;
import com.ttt.mim.domain.FactoryEval;
import com.ttt.mim.service.FactoryEvalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class FactoryEvalServiceImpl implements FactoryEvalService{
    private final FactoryEvalDao factoryEvalDao;
    @Autowired
    public FactoryEvalServiceImpl(FactoryEvalDao factoryEvalDao) { this.factoryEvalDao=factoryEvalDao; }

    @Override
    public List<FactoryEval> query(Map<String, Object> params) {
        return factoryEvalDao.query(params);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(FactoryEval factoryEval) {
        factoryEval.setCreateTime(new Date());
        int count =factoryEvalDao.add(factoryEval);
        if (count != 1) {
            throw new BusinessException("添加厂家信息失败:" + factoryEval);
        }
        return count;
    }

    @Override
    public FactoryEval findByUserId(Integer id){
        FactoryEval fac2=factoryEvalDao.findByUserId(id);
        if (fac2 == null){
            throw new DataNotFoundException("厂家评价不存在：" + id);
        }
        return fac2;
    }
    @Override
    public int count() {
        int num = factoryEvalDao.count();
        return num;
    }
}
