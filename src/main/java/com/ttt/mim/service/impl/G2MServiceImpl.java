package com.ttt.mim.service.impl;

import com.ttt.mim.dao.G2MDao;
import com.ttt.mim.domain.MenuInGroup;
import com.ttt.mim.service.G2MService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务实现
 * @author sunday
 */
@Service
public class G2MServiceImpl implements G2MService {
    private final G2MDao g2mDao;
    @Autowired
    public G2MServiceImpl(G2MDao g2mDao){this.g2mDao=g2mDao;}

    @Override
    public int addm2g(Integer[] menuIds, Integer groupId) {
        return g2mDao.addm2g(menuIds, groupId);
    }

    @Override
    public int delm2g(Integer[] ids) {
        return g2mDao.delm2g(ids);
    }

    @Override
    public List<MenuInGroup> queryMbyGid(Integer groupId) {
        return g2mDao.queryMbyGid(groupId);
    }
}
