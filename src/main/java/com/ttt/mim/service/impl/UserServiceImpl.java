package com.ttt.mim.service.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.ttt.mim.common.exception.BusinessException;
import com.ttt.mim.common.exception.DataAccessException;
import com.ttt.mim.common.exception.DataNotFoundException;
import com.ttt.mim.dao.UserDao;
import com.ttt.mim.domain.User;
import com.ttt.mim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 业务实现
 * @author a1recho
 * date 2019/8/5 15:00
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User checkLogin(String username, String password) {
        User user = userDao.findByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public int checkUpdate(User user) {
        User user1 = userDao.findByUsername(user.getUsername());
        if(user.getNewPsd() == user.getRePsd())
        {
            if(user.getPassword().equals(user1.getPassword())){
                return 1;
            }else{
                return 0;
            }
        }else{
            return 2;
        }
    }

    @Override
    public String getUsername(Integer id){
        return userDao.getUsername(id);
    }

    @Override
    public User getById(Integer id){
        User obj = userDao.getById(id);
        if (obj == null){
            throw new DataNotFoundException("不存在：" + id);
        }
        return obj;
    }

    @Override
    public List<User> query(Map<String, Object> params) {
        return userDao.query(params);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(User user){
        user.setCreateTime(new Date());
        user.setModifyTime(new Date());
        int count = userDao.add(user);
        if (count != 1) {
            throw new BusinessException("添加用户失败:" + user);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(User user){
        user.setModifyTime(new Date());
        int count = userDao.update((user));
        if (count != 1) {
            throw new BusinessException("更新数据失败：" + user);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer id){
        int count = userDao.delete(id);
        if (count != 1) {
            throw new BusinessException("删除用户失败", id);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(Integer[] ids){
        int count = userDao.batchDelete(ids);
        if (count != ids.length) {
            throw new DataAccessException("批量删除用户失败", ids);
        }
        return count;
    }

    @Override
    public int count() {
        int num = userDao.count();
        return num;
    }
}
