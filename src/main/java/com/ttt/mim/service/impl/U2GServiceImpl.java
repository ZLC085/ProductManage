package com.ttt.mim.service.impl;

import com.ttt.mim.dao.PriceDao;
import com.ttt.mim.dao.U2GDao;
import com.ttt.mim.domain.UserInGroup;
import com.ttt.mim.service.U2GService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 业务实现
 * @author sunday
 */
@Service
public class U2GServiceImpl implements U2GService {
    private final U2GDao u2gDao;
    @Autowired
    public U2GServiceImpl(U2GDao u2gDao){this.u2gDao=u2gDao;}

    @Override
    public int addu2g(Integer[] userIds, Integer groupId) {
        return u2gDao.addu2g(userIds, groupId);
    }

    @Override
    public int delu2g(Integer[] userIds, Integer groupId) {
        return u2gDao.delu2g(userIds, groupId);
    }

    @Override
    public List<UserInGroup> queryUbyGid(Integer groupId) {
        return u2gDao.queryUbyGid(groupId);
    }
}
