package com.ttt.mim.service.impl;
import com.ttt.mim.common.exception.BusinessException;
import com.ttt.mim.common.exception.DataAccessException;
import com.ttt.mim.common.exception.DataNotFoundException;
import com.ttt.mim.dao.CompanyDao;
import com.ttt.mim.domain.Company;
import com.ttt.mim.service.CompanyService;
import com.ttt.mim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao;
    @Autowired
    public CompanyServiceImpl(CompanyDao companyDao){
        this.companyDao = companyDao;
    }

    @Override
    public List<Company> query(Map<String, Object> params) {
        return companyDao.query(params);
    }

    @Override
    public Company getById(Integer id){
        Company obj = companyDao.getById(id);
        if (obj == null){
            throw new DataNotFoundException("不存在：" + id);
        }
        return obj;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Company company){
        company.setCreateTime(new Date());
        company.setModifyTime(new Date());
        int count = companyDao.add(company);
        if (count != 1) {
            throw new BusinessException("添加公司失败:" + company);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(Company company){
        company.setModifyTime(new Date());
        int count = companyDao.update((company));
        if (count != 1) {
            throw new BusinessException("更新数据失败：" + company);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer id){
        int count = companyDao.delete(id);
        if (count != 1) {
            throw new BusinessException("删除公司失败", id);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(Integer[] ids){
        int count = companyDao.batchDelete(ids);
        if (count != ids.length) {
            throw new DataAccessException("批量删除公司失败", ids);
        }
        return count;
    }

    @Override
    public int count() {
        int num = companyDao.count();
        return num;
    }
}
