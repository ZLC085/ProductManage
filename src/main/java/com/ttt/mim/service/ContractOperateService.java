package com.ttt.mim.service;

import com.ttt.mim.domain.ContractOperate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 合同操作  业务接口
 * @author zl
 * date 2019/8/5 15:00
 */
public interface ContractOperateService {

    /**
     * 根据id查询
     * @param id 查询条件
     * @return 实体对象
     */
    ContractOperate getById(Integer id);

    /**
     * 查询数据列表
     * @param params 查询条件
     * @return 数据列表
     */
    List<ContractOperate> query(Map<String, Object> params);

    /**
     * 添加
     * @param contractOperate 实体对象
     * @return 记录数
     */
    int add(ContractOperate contractOperate);

    /**
     * 修改
     * @param contractOperate 实体对象
     * @return 记录数
     */
    int update(ContractOperate contractOperate);

    /**
     * 删除单条记录
     * @param id 合同id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 批量删除
     * @param ids 合同id数组
     * @return 删除条数
     */
    int batchDelete(Integer[] ids);

    int upload(MultipartFile multipartFile, Integer id);

    void download(HttpServletRequest request, HttpServletResponse response, Integer id)throws IOException;

}
