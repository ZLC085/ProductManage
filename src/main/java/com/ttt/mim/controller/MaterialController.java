package com.ttt.mim.controller;

import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.Material;
import com.ttt.mim.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 物资
 * @author : mao yuhang
 * date : 2019-08-21 11:29
 */
@Controller
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/material/show")
    public String show() {
        return "/material/list";
    }

    @GetMapping("/materials")
    @ResponseBody
    public R query(@RequestParam Map<String, Object> params) {
        // 通过条件获取对应物资列表
        List<Material> list = materialService.query(params);
        return R.ok("返回数据列表", list, list.size(), null);
    }

    @GetMapping("/material/{cId}")
    public String showAddView(@PathVariable("cId") Integer cId, Model model) {
        // 获取物资分类名称
        //model.addAttribute("cName", categoryService.getById(cId).getName());
        return "/material/input?cId="+cId.toString();
    }

    @PostMapping("/material")
    @ResponseBody
    public R add(@RequestBody Material material) {
        int count = materialService.add(material);
        if (count > 0) {
            return R.ok("添加成功", null, count, "/materials");
        } else {
            return R.error("添加失败", null, count, null);
        }
    }

    @GetMapping("/material/{id}")
    public String showUpdateView(@PathVariable("id") Integer id, Model model) {
        // 获取该物资实体
        Material material = materialService.getById(id);
        model.addAttribute("material", material);
        // 获取物资分类名称
        //model.addAttribute("cName", categoryService.getById(material.getParent_id()).getName());
        return "material/edit?id="+id.toString();
    }

    @PutMapping("/material")
    @ResponseBody
    public R update(@RequestBody Material material) {
        int count = materialService.update(material);
        if (count > 0) {
            return R.ok("修改成功", null, count, "/materials");
        } else {
            return R.error("修改失败", null, count, null);
        }
    }

    @DeleteMapping("/material/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        int count = materialService.delete(id);
        if (count > 0) {
            return R.ok("删除成功", null, count, "/materials");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    @DeleteMapping("/material")
    @ResponseBody
    public R batchDelete(@RequestBody Integer[] ids) {
        int count = materialService.batchDelete(ids);
        if (count > 0) {
            return R.ok("删除成功", null, count, "/materials");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }
}
