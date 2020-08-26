package com.ttt.mim.controller;

import com.ttt.mim.common.utils.R;
import com.ttt.mim.common.utils.log.AddLogUser;
import com.ttt.mim.domain.LogSys;
import com.ttt.mim.service.LogSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class LogSysController {

    private final LogSysService logSysService;

    @Autowired
    public LogSysController(LogSysService logSysService) {
        this.logSysService = logSysService;
    }

    @GetMapping("/logSys")
    @AddLogUser(operation = "查看系统日志")
    public String show() {
        return "logSys";
    }

    @DeleteMapping("/logSys/{id}")
    @ResponseBody
    @AddLogUser(operation = "删除id={1}的系统日志")
    public R delete(@PathVariable("id") Integer id) {
        int count = logSysService.delete(id);

        if (count > 0) {
            return R.ok("删除成功", null, count, "/logSyses");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    @DeleteMapping("/logSys")
    @ResponseBody
    @AddLogUser(operation = "删除id={[1]}的系统日志")
    public R batchDelete(@RequestParam("ids[]") Integer[] ids) {
        int count = logSysService.batchDelete(ids);
        if (count > 0) {
            return R.ok("删除成功", null, count, "/logSyses");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    @GetMapping("/logSyses")
    @ResponseBody
    @AddLogUser(operation = "检索系统日志")
    public R query(@RequestParam Map<String, Object> params) {
        List<LogSys> logSysList = logSysService.query(params);
        int total = logSysService.count();

        return R.ok("返回数据列表", logSysList, total, null);
    }
}
