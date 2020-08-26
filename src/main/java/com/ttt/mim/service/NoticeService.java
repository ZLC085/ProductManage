package com.ttt.mim.service;
import com.ttt.mim.domain.Notice;

import java.util.List;
import java.util.Map;
public interface NoticeService {

    /**
     * 查询公告列表
     * @param params 查询条件
     * @return 公告列表
     */
    List<Notice> query(Map<String, Object> params);

    /**
     * 通过id获取公告信息
     * @param id 公告id
     * @return 实体对象
     */
    Notice getById(Integer id);

    /**
     * 添加公告
     * @param notice 实体对象
     * @return 记录数
     */
    int add(Notice notice);

    /**
     * 修改公告
     * @param notice 实体对象
     * @return 记录数
     */
    int update(Notice notice);

    /**
     * 删除单条公告
     * @param id 公告id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 批量删除公告
     * @param ids 公告id数组
     * @return 删除条数
     */
    int batchDelete(Integer[] ids);

    /**
     * 查询总记录
     * @return 记录数
     */
    int count();
}
