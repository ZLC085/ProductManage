package com.ttt.mim.service.impl;

import com.ttt.mim.dao.CustomerDao;
import com.ttt.mim.dao.CustomerListDao;
import com.ttt.mim.domain.Customer;
import com.ttt.mim.domain.CustomerList;
import com.ttt.mim.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户管理
 * @author JY
 */
@Service
public class CustomerServicelmpl implements CustomerService {
    private final CustomerDao customerDao;
    private final CustomerListDao customerListDao;

    @Autowired
    public CustomerServicelmpl(CustomerDao customerDao, CustomerListDao customerListDao) {
        this.customerDao = customerDao;
        this.customerListDao = customerListDao;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Customer customer) {
        customer.setCreateTime(new Date());
        customer.setModifyTime(new Date());
        int count = customerDao.add(customer);
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(Customer customer) {
        customer.setModifyTime(new Date());
        int count = customerDao.update(customer);
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer id) {
        int count = customerDao.delete(id);
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(Integer[] ids) {
        int count = customerDao.batchDelete(ids);
        return count;
    }

    @Override
    public Customer findById(Integer id) {
        return customerDao.findById(id);
    }

    @Override
    public Customer findByName(String name) {
        return customerDao.findByName(name);
    }

    @Override
    public List<CustomerList> query(Map<String, Object> params) {
        return customerListDao.query(params);
    }

    public int count(Map<String,Object> params){
        return customerListDao.count(params);
    }
}
