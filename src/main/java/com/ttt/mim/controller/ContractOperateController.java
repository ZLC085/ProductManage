package com.ttt.mim.controller;

import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.ContractOperate;
import com.ttt.mim.service.ContractOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 业务实现
 * @author zl
 * date 2019/8/19 20:00
 */
@Controller
@RequestMapping("/contract/operate")
public class ContractOperateController {

    private final ContractOperateService contractOperateService;
    @Autowired
    public ContractOperateController(ContractOperateService contractOperateService) {
        this.contractOperateService = contractOperateService;
    }


    @GetMapping("")
    public String show() {
        return "contract/operate/main";
    }


    @GetMapping("/query")
    @ResponseBody
    public List<ContractOperate> query(@RequestParam Map<String, Object> params) {
        return contractOperateService.query(params);
    }


    @GetMapping("/add/view")
    public String showAddView() {
        return "contract/operate/add";
    }


    @PostMapping("/add")
    @ResponseBody
    public R add(@RequestBody ContractOperate contractOperate) {
        if (contractOperateService.add(contractOperate) > 0){
            return R.ok("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateView(@PathVariable("id") Integer id, Model model) {
        ContractOperate contractOperate = contractOperateService.getById(id);
        model.addAttribute("contractOperate", contractOperate);
        return "contract/operate/update";
    }

    @PostMapping("/update")
    @ResponseBody
    public R update(@RequestBody ContractOperate contractOperate) {
        if (contractOperateService.update(contractOperate) > 0){
            return R.ok("修改成功");
        } else {
            return R.error("修改失败");
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        if (contractOperateService.delete(id) > 0){
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    @DeleteMapping("/batchDelete")
    @ResponseBody
    public R batchDelete(@RequestBody Integer[] ids) {
        if (contractOperateService.batchDelete(ids) > 0){
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    @GetMapping("/file/upload/view/{id}")
    public String showUploadView(@PathVariable("id") Integer id, Model model) {
        ContractOperate contractOperate = contractOperateService.getById(id);
        model.addAttribute("contractOperate", contractOperate);
        return "contract/operate/upload";
    }

    @RequestMapping("/file/upload/{id}")
    public R upload(@RequestParam("file")MultipartFile multipartFile, @PathVariable("id") Integer id){
        if (multipartFile !=null && !multipartFile.isEmpty()){
            int count = contractOperateService.upload(multipartFile, id);
            if (count > 0){
                return R.ok("上传成功");
            } else {
                return R.error("删除失败");
            }
        }
        return R.error("上传文件为空，请选择文件。");
    }

    @RequestMapping("/file/download/{id}")
    public void download(HttpServletRequest request,
                         HttpServletResponse response,
                         @PathVariable("id") Integer id) throws IOException {
        contractOperateService.download(request, response, id);
    }
}
