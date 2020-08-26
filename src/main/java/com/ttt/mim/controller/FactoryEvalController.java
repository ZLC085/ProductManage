package com.ttt.mim.controller;

import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.FactoryEval;
import com.ttt.mim.service.FactoryEvalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
/**
 * 厂家管理
 * @author zlc
 * date 2019/8/25 18:00
 */
@Controller
public class FactoryEvalController {

    private final FactoryEvalService factoryEvalService;

    @Autowired
    public FactoryEvalController(FactoryEvalService factoryEvalService) {
        this.factoryEvalService = factoryEvalService;
    }


    @GetMapping("/factory/eval/show")
    public String show() {
        return "factory/eval/list";
    }

    @GetMapping("/factory/evals")
    @ResponseBody
    public R query(@RequestParam Map<String, Object> params) {
        List<FactoryEval> list=factoryEvalService.query(params);
        int total=factoryEvalService.count();
        return R.ok("返回数据列表", list,total, null);
    }

    @GetMapping("/factory/eval")
    public String showAddView() {
        return "factory/eval/input";
    }

    @PostMapping("/factory/eval")
    @ResponseBody
    public R add(@RequestBody FactoryEval factoryEval) {
        int count=factoryEvalService.add(factoryEval);
        if (count>0) {
            return R.ok("添加成功", null, count, "/factorys");
        } else {
            return R.error("添加失败", null, count, null);
        }
    }
}