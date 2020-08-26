package com.ttt.mim.controller;

import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.Group;
import com.ttt.mim.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户组Controller
 * @author swj
 * @Data
 */
@Controller
public class GroupController {
    private final GroupService groupService;
    @Autowired
    public GroupController(GroupService groupService){this.groupService = groupService;}

    @GetMapping("/group/show")
    public String show() {
        return "group/main/list";
    }

    @GetMapping("/group")
    public String showAddView() {
        return "group/mian/input";
    }

    @PostMapping("/group")
    @ResponseBody
    public R add(@RequestBody Group group) {
        int count =groupService.add(group);
        if (count > 0) {
            return R.ok("添加成功",null,count,"/groups");
        } else {
            return R.error("添加失败",null,count,null);
        }
    }

    @DeleteMapping("/group/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer[] ids) {
        int count=groupService.delete(ids);
        if ( count> 0) {
            return R.ok("删除成功",null,count,"/groups");
        } else {
            return R.error("删除失败",null,count,null);
        }
    }

    @PutMapping("/group")
    @ResponseBody
    public R update(@RequestParam("group") Group group) {
        int count =groupService.update(group);
        if (count > 0) {
            return R.ok("修改成功",null,count,"/groups");
        } else {
            return R.error("修改失败",null,count,null);
        }
    }

    @GetMapping("/groups")
    @ResponseBody
    public List<Group> query(@RequestParam Map<String, Object> params) {
        return groupService.query(params);
    }
}
