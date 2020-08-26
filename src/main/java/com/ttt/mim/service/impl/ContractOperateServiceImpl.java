package com.ttt.mim.service.impl;

import com.ttt.mim.common.exception.BusinessException;
import com.ttt.mim.common.exception.DataAccessException;
import com.ttt.mim.common.exception.DataNotFoundException;
import com.ttt.mim.dao.ContractOperateDao;
import com.ttt.mim.dao.ContractOperateDao;
import com.ttt.mim.domain.ContractOperate;
import com.ttt.mim.service.ContractOperateService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 业务实现
 * @author zl
 * date 2019/8/19 10:00
 */
@Service
public class ContractOperateServiceImpl implements ContractOperateService {

    private final ContractOperateDao contractOperateDao;
    @Autowired
    public ContractOperateServiceImpl(ContractOperateDao contractOperateDao) {
        this.contractOperateDao = contractOperateDao;
    }

    @Override
    public ContractOperate getById(Integer id) {
        ContractOperate obj = contractOperateDao.getById(id);
        if (obj == null){
            throw new DataNotFoundException("不存在：" + id);
        }
        return obj;
    }

    @Override
    public List<ContractOperate> query(Map<String, Object> params) {
        return contractOperateDao.query(params);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(ContractOperate contractOperate) {
        contractOperate.setCreateTime(new Date());
        contractOperate.setModifyTime(new Date());
        int count = contractOperateDao.add(contractOperate);
        if (count != 1) {
            throw new BusinessException("添加合同失败:" + contractOperate);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(ContractOperate contractOperate) {
        contractOperate.setModifyTime(new Date());
        int count = contractOperateDao.update(contractOperate);
        if (count != 1) {
            throw new BusinessException("更新数据失败：" + contractOperate);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer id) {
        int count = contractOperateDao.delete(id);
        if (count != 1) {
            throw new BusinessException("删除合同失败", id);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(Integer[] ids) {
        int count = contractOperateDao.batchDelete(ids);
        if (count != ids.length) {
            throw new DataAccessException("批量删除合同失败", ids);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int upload(MultipartFile multipartFile, Integer id) {
        String originalFilename = multipartFile.getOriginalFilename();//获取原始文件名
        System.out.println(originalFilename);
        ContractOperate contractOperate = new ContractOperate();
        try {
            byte[] bs = multipartFile.getBytes();
            long fileSize = bs.length;
            contractOperate.setId(id);
            contractOperate.setCheckFile(bs);
            contractOperate.setCheckFilesize(fileSize);
            contractOperate.setModifyTime(new Date());
        } catch (IOException e){
            e.printStackTrace();
        }
        int count = contractOperateDao.update(contractOperate);
        if (count != 1) {
            throw new BusinessException("更新数据失败：" + contractOperate);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException {
        response.setCharacterEncoding("utf-8");
        ContractOperate contractOperate = contractOperateDao.getById(id);
        byte[] bytes = contractOperate.getCheckFile();
        String fileName = contractOperate.getCheckFilename();
        if (bytes != null){
            String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
            response.setContentType("application/"+suffix);
            String filename = filenameEncoding(fileName, request);
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);
            InputStream fis = null;
            try{
                fis = new ByteArrayInputStream(bytes);
                IOUtils.copy(fis, response.getOutputStream());
                response.flushBuffer();
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        response.setContentType("text/html; charset=utf8");
                        response.getWriter().print("下载失败,文件或已丢失!");
                    }
                }
            }
        }
    }

    //用来对下载的文件名称进行编码
    private static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
        String agent = request.getHeader("User-Agent"); //获取浏览器
        if (agent.contains("Firefox")) {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?"
                    + base64Encoder.encode(filename.getBytes(StandardCharsets.UTF_8))
                    + "?=";
        } else if(agent.contains("MSIE")) {
            filename = URLEncoder.encode(filename, "utf-8");
        } else {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

}
