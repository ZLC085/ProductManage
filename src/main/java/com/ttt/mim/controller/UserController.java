package com.ttt.mim.controller;
import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.User;
import com.ttt.mim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * @author wangerpei
 * date 2019/8/19 20:00
 */
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/show")
    public String show() {
        return "user/list";
    }

    @GetMapping("/users")
    @ResponseBody
    public R query(@RequestParam Map<String, Object> params) {
        List<User> list = userService.query(params);
        int total = userService.count();
        return R.ok("返回数据列表", list, total, null);
    }

    /**
     * 请求添加界面
     */
    @GetMapping("/user")
    public String showAddView() {
        return "user/input";
    }

    /**
     * 添加提交
     */
    @PostMapping("/user")
    @ResponseBody
    public R add(@RequestBody User user) {
        int count = userService.add(user);
        if (count > 0) {
            return R.ok("添加成功", null, count, "/users");
        } else {
            return R.error("添加失败", null, count, null);
        }
    }

    /**
     * 请求修改页面
     */
    @GetMapping("/user/{id}")
    public String showUpdateView(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    /**
     * 修改提交
     */
    @PutMapping("/user")
    @ResponseBody
    public R edit(@RequestBody User user) {
        int count = userService.update(user);
        if (count > 0) {
            return R.ok("提交成功", null, count, "/users");
        } else {
            return R.error("提交失败", null, count, null);
        }
    }

    /**
     * 单个删除提交
     */
    @DeleteMapping("/user/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        int count = userService.delete(id);
        if (count > 0) {
            return R.ok("删除成功", null, count, "/users");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    /**
     * 批量删除提交
     */
    @DeleteMapping("/user")
    @ResponseBody
    public R batchDelete(@RequestBody Integer[] ids) {
        int count = userService.batchDelete(ids);
        if (count > 0) {
            return R.ok("删除成功", null, count, "/users");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }


    /**
     * 查看用户详情
     */
    @GetMapping("/userView/{id}")
    @ResponseBody
    public String query(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user/details";
    }

    /**
     * 请求个人中心页面
     */
    @GetMapping("/userInfo/{id}")
    public String UserInfoUpdateView(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user/userinfo";
    }

    /**
     * 个人设置提交
     */
    @PostMapping("/userInfo")
    public R UserInfoEdit(@RequestBody User user) {
        int result = userService.checkUpdate(user);
        if (result == 1)
        {
            int count = userService.update(user);
            if (count > 0) {
                return R.ok("提交成功", null, count, "/");
            } else {
                return R.error("提交失败", null, count, null);
            }
        } else if (result == 0) {
            return R.error("两次输入不一致！");
        } else if (result == 2) {
            return R.error("原密码错误！");
        }
        return null;
    }
}
