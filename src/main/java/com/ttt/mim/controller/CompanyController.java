package com.ttt.mim.controller;
import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.Company;
import com.ttt.mim.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * 公司管理
 * @author wangerpei
 * date 2019/8/19 20:00
 */
@Controller
public class CompanyController {

    private final CompanyService companyService;
    @Autowired
    public CompanyController(CompanyService companyService){
        this.companyService=companyService;
    }


    /**
     * 请求显示默认页面
     */
    @GetMapping("/company/show")
    public String show() {
        return "company/list";
    }

    /**
     * 请求数据
     */
    @GetMapping("/companys")
    @ResponseBody
    public R query(@RequestParam Map<String, Object> params) {
        List<Company> list =companyService.query(params);
        int total = companyService.count();
        return R.ok("返回数据列表", list, total, null);
    }

    /**
     * 请求添加页面
     */
    @GetMapping("/company")
    public String showAddView() {
        return "company/input";
    }

    /**
     * 添加提交
     */
    @PostMapping("/company")
    @ResponseBody
    public R add(@RequestBody Company company) {
        int count =companyService.add(company);
        if (count > 0){
            return R.ok("添加成功", null, count, "/companys");
        } else {
            return R.error("添加失败", null, count, null);
        }
    }

    /**
     * 请求修改页面
     */
    @GetMapping("/company/{id}")
    public String showUpdateView(@PathVariable("id") Integer id, Model model) {
        Company company = companyService.getById(id);
        model.addAttribute("company", company);
        return "company/edit";
    }

    /**
     * 修改提交
     */
    @PutMapping("/company")
    @ResponseBody
    public R edit(@RequestBody Company company) {
        int count = companyService.update(company);
        if (count > 0){
            return R.ok("提交成功", null, count, "/companys");
        } else {
            return R.error("提交失败", null, count, null);
        }
    }

    /**
     * 单个删除提交
     */
    @DeleteMapping("/company/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        int count =companyService.delete(id);
        if (count > 0){
            return R.ok("删除成功", null, count, "/companys");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    /**
     * 批量删除提交
     */
    @DeleteMapping("/company")
    @ResponseBody
    public R batchDelete(@RequestBody Integer[] ids) {
        int count= companyService.batchDelete(ids);
        if (count > 0){
            return R.ok("删除成功", null, count, "/companys");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    /**
     * 查看公司详情
     */
    @GetMapping("/companyView/{id}")
    @ResponseBody
    public String query(@PathVariable("id") Integer id,Model model) {
        Company company = companyService.getById(id);
        model.addAttribute("company", company);
        return "company/details";
    }
}
