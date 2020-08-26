package com.ttt.mim.dao;
import com.ttt.mim.domain.Company;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 *公司管理
 */
@Repository
public interface CompanyDao {

    /**
     * 查询公司列表
     * @param params 参数集合
     * @return 公司列表
     */
    List<Company> query(Map<String, Object> params);

    /**
     * 通过id获取公司信息
     * @param id 公司id
     * @return 公司实体
     */
    Company getById(Integer id);

    /**
     * 添加公司
     * @param company 公司实体
     * @return 添加个数
     */
    int add(Company company);

    /**
     * 修改公司
     * @param company 公司实体
     * @return 修改个数
     */
    int update(Company company);

    /**
     * 删除单条公司
     * @param id 公司Id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 批量删除公司
     * @param ids 公司Id数组
     * @return 删除个数
     */
    int batchDelete(Integer[] ids);

    /**
     * 查询总记录
     * @return 总记录条数
     */
    int count();
}