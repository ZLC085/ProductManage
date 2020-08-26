package com.ttt.mim.controller;

import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.MenuInGroup;
import com.ttt.mim.service.G2MService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户组菜单Controller
 * @author swj
 * @date 2019/08/26
 */
@Controller
public class G2MController {
    private final G2MService g2mService;
    @Autowired
    public G2MController(G2MService g2mService){this.g2mService=g2mService;}

    @GetMapping("/g2m/show")
    public String show() {
        return "g2m/main/list";
    }

    @GetMapping("/g2m")
    public String showAddView() {
        return "g2m/main/input";
    }

    @PostMapping("/g2m")
    @ResponseBody
    public R add(@RequestBody Integer[] menuIds, Integer groupId) {
        int count =g2mService.addm2g(menuIds, groupId);
        if (count > 0) {
            return R.ok("添加成功",null,count,"/queryMbyGid");
        } else {
            return R.error("添加失败",null,count,null);
        }
    }
    @DeleteMapping("/g2m/{ids}")
    @ResponseBody
    public R delete(@PathVariable("ids") Integer[] ids) {
        int count =g2mService.delm2g(ids);
        if (count > 0) {
            return R.ok("删除成功",null,count,"/queryMbyGid");
        } else {
            return R.error("删除失败",null,count,null);
        }
    }
    @GetMapping("/queryMbyGid")
    @ResponseBody
    public List<MenuInGroup> query(@RequestBody Integer groupId) {
        return g2mService.queryMbyGid(groupId);
    }
}
