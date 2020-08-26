package com.ttt.mim.service.impl;


import com.ttt.mim.common.exception.BusinessException;
import com.ttt.mim.dao.MenuDao;
import com.ttt.mim.domain.Menu;
import com.ttt.mim.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    private  final MenuDao menuDao;

    public MenuServiceImpl(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public Menu getById(Integer id) {
        Menu menu=menuDao.getById(id);
        return menu;
    }

    @Override
    public List<Menu> query(Map<String, Object> param) {
        return menuDao.query(param);
    }

    @Override
    public int delete(Integer id) {
        int count=menuDao.delete(id);
        return count;
    }

    @Override
    public int batchDelete(Integer[] ids) {
        int count=menuDao.batchDelete(ids);
        return count;
    }

    @Override
    public int add(Menu menu) {
        menu.setCreateTime(new Date());
        menu.setModifyTime(new Date());
        int count=menuDao.add(menu);
        if(count!=1){
            throw new BusinessException("添加菜单失败:"+ menu);
        }
        return count;
    }

    @Override
    public int update(Menu menu) {
        menu.setModifyTime(new Date());
        int count=menuDao.update(menu);
        if(count!=1){
            throw new BusinessException("更新数据失败:"+ menu);
        }
        return count;
    }

    @Override
    public int count() {
        int num=menuDao.count();
        return num;
    }


}
