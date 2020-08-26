package com.ttt.mim.controller;

import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.ContractMain;
import com.ttt.mim.service.ContractMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * 业务实现
 * @author zl
 * date 2019/8/19 20:00
 */
@Controller
public class ContractMainController {

    private final ContractMainService contractMainService;
    @Autowired
    public ContractMainController(ContractMainService contractMainService) {
        this.contractMainService = contractMainService;
    }

    @GetMapping("/contract/show")
    public String show() {
        return "contract/list";
    }

    @GetMapping("/contract/list")
    @ResponseBody
    public R query(@RequestParam Map<String, Object> params) {
        List<ContractMain> list = contractMainService.query(params);
        int total = contractMainService.count();
        return R.ok("返回数据列表", list, total, null);
    }

    @GetMapping("/contract")
    public String showAddView() {
        return "contract/input";
    }

    @PostMapping("/contract")
    @ResponseBody
    public R add(@RequestBody ContractMain contractMain) {
        int count = contractMainService.add(contractMain);
        if (count > 0){
            return R.ok("添加成功", null, count, "/contracts");
        } else {
            return R.error("添加失败", null, count, null);
        }
    }

    @GetMapping("/contract/{id}")
    public String showUpdateView(@PathVariable("id") Integer id, Model model) {
        ContractMain contractMain = contractMainService.getById(id);
        model.addAttribute("contractMain", contractMain);
        return "contract/main/edit";
    }

    @PutMapping("/contract")
    @ResponseBody
    public R submit(@RequestBody ContractMain contractMain) {
        int count = contractMainService.submit(contractMain);
        if (count > 0){
            return R.ok("提交成功", null, count, "/contracts");
        } else {
            return R.error("提交失败", null, count, null);
        }
    }

    @PutMapping("/con2")
    @ResponseBody
    public R approve(@RequestBody ContractMain contractMain) {
        int count = contractMainService.approve(contractMain);
        if (count > 0){
            return R.ok("审批成功", null, count, "/contracts");
        } else {
            return R.error("审批失败", null, count, null);
        }
    }

    @DeleteMapping("/contract/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        int count = contractMainService.delete(id);
        if (count > 0){
            return R.ok("删除成功", null, count, "/contracts");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    @DeleteMapping("/contract")
    @ResponseBody
    public R batchDelete(@RequestBody Integer[] ids) {
        int count = contractMainService.batchDelete(ids);
        if (count > 0){
            return R.ok("删除成功", null, count, "/contracts");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    @GetMapping("/contract/upload/{id}")
    public String showUploadView(@PathVariable("id") Integer id, Model model) {
        ContractMain contractMain = contractMainService.getById(id);
        model.addAttribute("contractMain", contractMain);
        return "contract/main/upload";
    }

    @PostMapping("/contract/upload")
    @ResponseBody
    public R upload(@RequestParam("file")MultipartFile multipartFile,
                    @RequestParam("id") Integer id){
        if (multipartFile !=null && !multipartFile.isEmpty()){
            int count = contractMainService.upload(multipartFile, id);
            if (count > 0){
                return R.ok("上传成功", null, count, "/contracts");
            } else {
                return R.error("删除失败", null, count, null);
            }
        }
        return R.error("上传文件为空,请选择文件!", null, null, null);
    }

    @RequestMapping("/contract/download/{id}")
    public void download(HttpServletRequest request,
                         HttpServletResponse response,
                         @PathVariable("id") Integer id) throws IOException {
        contractMainService.download(request, response, id);
    }
}
