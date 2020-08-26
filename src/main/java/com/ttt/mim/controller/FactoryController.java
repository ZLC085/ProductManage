package com.ttt.mim.controller;

import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.Factory;
import com.ttt.mim.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 厂家管理
 * @author zlc
 * date 2019/8/25 18:00
 */
@Controller
public class FactoryController {

    private final FactoryService factoryService;

    @Autowired
    public FactoryController(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    /**
     * 请求显示默认页面
     */
    @GetMapping("/factory/show")
    public String show() {
        return "factory/main/list";
    }

    /**
     * 查询
     */
    @GetMapping("/factorys")
    @ResponseBody
    public R query(@RequestParam Map<String, Object> params) {
        List<Factory> list= factoryService.query(params);
        int total=factoryService.count();
        return R.ok("返回数据列表", list,total, null);
    }

    /**
     * 添加页面
     */
    @GetMapping("/factory")
    public String showInputView() {
        return "factory/main/input";
    }

    /**
     * 添加提交
     */
    @PostMapping("/factory")
    @ResponseBody
    public R input(@RequestBody Factory factory) {
        int count=factoryService.add(factory);
        if (count>0) {
            return R.ok("添加成功", null, count, "/factorys");
        } else {
            return R.error("添加失败", null, count, null);
        }
    }

    /**
     * 修改页面
     */
    @GetMapping("/factory/{id}")
    public String showUpdateView(@PathVariable("id") Integer id, Model model) {
        Factory factory = factoryService.findById(id);
        model.addAttribute("factory", factory);
        return "factory/main/edit";
    }

    /**
     * 修改提交
     */
    @PutMapping("/factory")
    @ResponseBody
    public R edit(@RequestBody Factory factory) {
        int count=factoryService.update(factory);
        if (count > 0) {
            return R.ok("修改成功", null, count, "/factorys");
        } else {
            return R.error("修改失败", null, count, null);
        }
    }

    /**
     * 根据id删除
     */
    @DeleteMapping("/factory/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
       int count=factoryService.delete(id);
        if (count > 0){
            return R.ok("删除成功", null, count, "/factorys");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/factory")
    @ResponseBody
    public R batchDelete(@RequestBody Integer[] ids) {
        int count=factoryService.batchDelete(ids);
        if (count > 0){
            return R.ok("删除成功", null, count, "/factorys");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    /**
     * 查看详情
     */
    @GetMapping("/factory/detail/{id}")
    public String showDetailView(@PathVariable("id") Integer id, Model model) {
        Factory factory = factoryService.findById(id);
        model.addAttribute("factory", factory);
        return "factory/main/detail";
    }
}