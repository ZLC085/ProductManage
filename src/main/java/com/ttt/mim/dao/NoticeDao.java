package com.ttt.mim.dao;
import com.ttt.mim.domain.Notice;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 *通知公告
 */
@Repository
public interface NoticeDao {

    /**
     * 查询公告列表
     * @param params 参数集合
     * @return 公告列表
     */
    List<Notice> query(Map<String, Object> params);

    /**
     * 通过id获取公告信息
     * @param id 公告id
     * @return 公告实体
     */
    Notice getById(Integer id);

    /**
     * 添加公告
     * @param notice 公告实体
     * @return 添加个数
     */
    int add(Notice notice);

    /**
     * 修改公告
     * @param notice 公告实体
     * @return 修改个数
     */
    int update(Notice notice);

    /**
     * 删除单条公告
     * @param id 公告Id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 批量删除公告
     * @param ids 公告Id数组
     * @return 删除个数
     */
    int batchDelete(Integer[] ids);

    /**
     * 查询总记录
     * @return 总记录条数
     */
    int count();
}