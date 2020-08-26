package com.ttt.mim.controller;
import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.Notice;
import com.ttt.mim.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * 通知公告
 * @author wangerpei
 * date 2019/8/19 20:00
 */
@Controller
public class NoticeController {

    private final NoticeService noticeService;
    @Autowired
    public NoticeController(NoticeService noticeService){
        this.noticeService=noticeService;
    }

    @GetMapping("/notice/show")
    public String show() {
        return "notice/list";
    }

    @GetMapping("/notices")
    @ResponseBody
    public R query(@RequestParam Map<String, Object> params) {
        List<Notice> list = noticeService.query(params);
        int total = noticeService.count();
        return R.ok("返回数据列表", list, total, null);
    }


    /**
     * 请求添加界面
     */
    @GetMapping("/notice")
    public String showAddView(){
        return "notice/input";
    }

    /**
     * 添加提交
     */
    @PostMapping("/notice")
    @ResponseBody
    public R add(@RequestBody Notice notice) {
        int count = noticeService.add(notice);
        if (count > 0){
            return R.ok("添加成功", null, count, "/notices");
        } else {
            return R.error("添加失败", null, count, null);
        }
    }

    /**
     * 请求修改页面
     */
    @GetMapping("/notice/{id}")
    public String showUpdateView(@PathVariable("id") Integer id, Model model) {
        Notice notice = noticeService.getById(id);
        model.addAttribute("notice", notice);
        return "notice/edit";
    }

    /**
     * 修改提交
     */
    @PutMapping("/notice")
    @ResponseBody
    public R edit(@RequestBody Notice notice) {
        int count = noticeService.update(notice);
        if (count > 0){
            return R.ok("提交成功", null, count, "/notices");
        } else {
            return R.error("提交失败", null, count, null);
        }
    }

    /**
     * 单个删除提交
     */
    @DeleteMapping("/notice/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        int count = noticeService.delete(id);
        if (count > 0){
            return R.ok("删除成功", null, count, "/notices");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    /**
     * 批量删除提交
     */
    @DeleteMapping("/notice")
    @ResponseBody
    public R batchDelete(@RequestBody Integer[] ids) {
        int count = noticeService.batchDelete(ids);
        if (count > 0){
            return R.ok("删除成功", null, count, "/notices");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

}
