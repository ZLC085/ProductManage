package com.ttt.mim.service;

import com.ttt.mim.domain.ContractMain;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 业务接口
 * @author zl
 * date 2019/8/5 15:00
 */
public interface ContractMainService {

    /**
     * 根据id查询合同
     * @param id 查询条件
     * @return 实体对象
     */
    ContractMain getById(Integer id);

    /**
     * 查询总记录
     * @return 记录数
     */
    int count();

    /**
     * 查询合同列表
     * @param params 查询条件
     * @return 合同列表
     */
    List<ContractMain> query(Map<String, Object> params);

    /**
     * 添加合同
     * @param contractMain 实体对象
     * @return 记录数
     */
    int add(ContractMain contractMain);

    /**
     * 提交合同
     * @param ContractMain 实体对象
     * @return 记录数
     */
    int submit(ContractMain ContractMain);

    /**
     * 审批合同
     * @param ContractMain 实体对象
     * @return 记录数
     */
    int approve(ContractMain ContractMain);

    /**
     * 删除单个合同
     * @param id 合同id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 批量删除合同
     * @param ids 合同id数组
     * @return 删除条数
     */
    int batchDelete(Integer[] ids);

    int upload(MultipartFile multipartFile, Integer id);

    void download(HttpServletRequest request, HttpServletResponse response, Integer id)throws IOException;

}
