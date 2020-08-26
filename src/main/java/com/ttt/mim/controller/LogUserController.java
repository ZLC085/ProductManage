package com.ttt.mim.controller;

import com.ttt.mim.common.utils.R;
import com.ttt.mim.common.utils.log.AddLogUser;
import com.ttt.mim.domain.LogUser;
import com.ttt.mim.service.LogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class LogUserController {

    private final LogUserService logUserService;

    @Autowired
    public LogUserController(LogUserService logUserService) {
        this.logUserService = logUserService;
    }

    @GetMapping("/logUser")
    @AddLogUser(operation = "查看用户日志")
    public String show() {
        return "logUser";
    }

    @DeleteMapping("/logUser/{id}")
    @ResponseBody
    @AddLogUser(operation = "删除id={1}的用户日志")
    public R delete(@PathVariable("id") Integer id) {
        int count = logUserService.delete(id);

        if (count > 0) {
            return R.ok("删除成功", null, count, "/logUsers");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    @DeleteMapping("/logUser")
    @ResponseBody
    @AddLogUser(operation = "删除id={[1]}的用户日志")
    public R batchDelete(@RequestParam("ids[]") Integer[] ids) {
        int count = logUserService.batchDelete(ids);

        if (count > 0) {
            return R.ok("删除成功", null, count, "/logUsers");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    @GetMapping("/logUsers")
    @ResponseBody
    @AddLogUser(operation = "检索用户日志")
    public R query(@RequestParam Map<String, Object> params) {
        List<LogUser> logUserList = logUserService.query(params);
        int total = logUserService.count();

        return R.ok("返回数据列表", logUserList, total, null);
    }
}
