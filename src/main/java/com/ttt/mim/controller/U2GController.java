package com.ttt.mim.controller;

import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.UserInGroup;
import com.ttt.mim.service.U2GService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class U2GController {
    private U2GService u2gService;
    @Autowired
    public U2GController(U2GService u2gService){this.u2gService = u2gService;}

    @GetMapping("u2g/show")
    public String show() {
        return "u2g/main/list";
    }

    @GetMapping("/u2g")
    public String showAddView() {
        return "u2g/main/input";
    }

    @PostMapping("/u2g")
    @ResponseBody
    public R add(@RequestBody Integer[] userIds,Integer groupId) {
        int count =u2gService.addu2g(userIds, groupId);
        if (count > 0) {
            return R.ok("添加成功",null,count,"/queryUbyGid");
        } else {
            return R.error("添加失败",null,count,null);
        }
    }
    @DeleteMapping("/u2g/{user_id}")
    @ResponseBody
    public R delete(@PathVariable("user_id") Integer[] userIds,Integer groupId) {
        int count =u2gService.delu2g(userIds, groupId);
        if (count > 0) {
            return R.ok("删除成功",null,count,"/queryUbyGid");
        } else {
            return R.error("删除失败",null,count,null);
        }
    }
    @GetMapping("/queryUbyGid")
    @ResponseBody
    public List<UserInGroup> query(@RequestBody Integer groupId) {
        return u2gService.queryUbyGid(groupId);
    }
}
