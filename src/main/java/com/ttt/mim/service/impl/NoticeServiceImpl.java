package com.ttt.mim.service.impl;
import com.ttt.mim.common.exception.BusinessException;
import com.ttt.mim.common.exception.DataAccessException;
import com.ttt.mim.common.exception.DataNotFoundException;
import com.ttt.mim.dao.NoticeDao;
import com.ttt.mim.domain.Notice;
import com.ttt.mim.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeDao noticeDao;

    @Autowired
    public NoticeServiceImpl(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    @Override
    public List<Notice> query(Map<String, Object> params) {
        return noticeDao.query(params);
    }

    @Override
    public Notice getById(Integer id){
        Notice obj = noticeDao.getById(id);
        if (obj == null){
            throw new DataNotFoundException("不存在：" + id);
        }
        return obj;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Notice notice) {
        notice.setCreateTime(new Date());
        notice.setModifyTime(new Date());
        int count = noticeDao.add(notice);
        if (count != 1) {
            throw new BusinessException("添加公告失败:" + notice);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(Notice notice) {
        notice.setModifyTime(new Date());
        int count = noticeDao.update((notice));
        if (count != 1) {
            throw new BusinessException("更新数据失败：" + notice);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer id) {
        int count = noticeDao.delete(id);
        if (count != 1) {
            throw new BusinessException("删除公告失败", id);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(Integer[] ids) {
        int count = noticeDao.batchDelete(ids);
        if (count != ids.length) {
            throw new DataAccessException("批量删除公告失败", ids);
        }
        return count;
    }

    @Override
    public int count() {
        int num = noticeDao.count();
        return num;
    }
}
