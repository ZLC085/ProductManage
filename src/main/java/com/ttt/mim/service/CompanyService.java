package com.ttt.mim.service;
import com.ttt.mim.domain.Company;
import com.ttt.mim.domain.Notice;

import java.util.List;
import java.util.Map;
public interface CompanyService {

    /**
     * 查询公司列表
     * @param params 查询条件
     * @return 公司列表
     */
    List<Company> query(Map<String, Object> params);

    /**
     * 通过id获取公司信息
     * @param id 公司id
     * @return 实体对象
     */
    Company getById(Integer id);

    /**
     * 添加公司
     * @param company 实体对象
     * @return 记录数
     */
    int add(Company company);

    /**
     * 修改公司
     * @param company 实体对象
     * @return 记录数
     */
    int update(Company company);

    /**
     * 删除单个公司
     * @param id 公司id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 批量删除用户
     * @param ids 用户id数组
     * @return 删除条数
     */
    int batchDelete(Integer[] ids);

    /**
     * 查询总记录
     * @return 记录数
     */
    int count();
}
