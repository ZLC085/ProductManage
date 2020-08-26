package com.ttt.mim.service.impl;

import com.ttt.mim.common.exception.BusinessException;
import com.ttt.mim.common.exception.DataAccessException;
import com.ttt.mim.common.exception.DataNotFoundException;
import com.ttt.mim.dao.ContractMainDao;
import com.ttt.mim.domain.ContractMain;
import com.ttt.mim.service.ContractMainService;
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
public class ContractMainServiceImpl implements ContractMainService {

    private final ContractMainDao contractMainDao;
    @Autowired
    public ContractMainServiceImpl(ContractMainDao contractMainDao) {
        this.contractMainDao = contractMainDao;
    }

    @Override
    public ContractMain getById(Integer id) {
        ContractMain obj = contractMainDao.getById(id);
        if (obj == null){
            throw new DataNotFoundException("不存在：" + id);
        }
        return obj;
    }

    @Override
    public int count() {
        int num = contractMainDao.count();
        return num;
    }

    @Override
    public List<ContractMain> query(Map<String, Object> params) {
        return contractMainDao.query(params);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(ContractMain contractMain) {
        contractMain.setCreateTime(new Date());
        contractMain.setModifyTime(new Date());
        contractMain.setStatus(false);
        int count = contractMainDao.add(contractMain);
        if (count != 1) {
            throw new BusinessException("添加合同失败:" + contractMain);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int submit(ContractMain contractMain) {
        contractMain.setSubmitTime(new Date());
        contractMain.setStatus(true);
        int count = contractMainDao.update(contractMain);
        if (count != 1) {
            throw new BusinessException("更新数据失败：" + contractMain);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int approve(ContractMain contractMain) {
        contractMain.setApproveTime(new Date());
        int count = contractMainDao.update(contractMain);
        if (count != 1) {
            throw new BusinessException("更新数据失败：" + contractMain);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer id) {
        int count = contractMainDao.delete(id);
        if (count != 1) {
            throw new BusinessException("删除合同失败", id);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(Integer[] ids) {
        int count = contractMainDao.batchDelete(ids);
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
        ContractMain contractMain = new ContractMain();
        try {
            byte[] bs = multipartFile.getBytes();
            long fileSize = bs.length;
            contractMain.setId(id);
            contractMain.setFile(bs);
            contractMain.setFilesize(fileSize);
            contractMain.setModifyTime(new Date());
        } catch (IOException e){
            e.printStackTrace();
        }
        int count = contractMainDao.update(contractMain);
        if (count != 1) {
            throw new BusinessException("更新数据失败：" + contractMain);
        }
        return count;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException {
        response.setCharacterEncoding("utf-8");
        ContractMain contractMain = contractMainDao.getById(id);
        byte[] bytes = contractMain.getFile();
        String fileName = contractMain.getFilename();
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
