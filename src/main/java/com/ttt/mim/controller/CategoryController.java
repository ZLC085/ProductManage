package com.ttt.mim.controller;

import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.Category;
import com.ttt.mim.service.CategoryService;
import com.ttt.mim.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 物资分类
 * @author : mao yuhang
 * date : 2019-08-20 17:29
 */
@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final MaterialService materialService;

    @Autowired
    public CategoryController(CategoryService categoryService, MaterialService materialService) {
        this.categoryService = categoryService;
        this.materialService = materialService;
    }

    @GetMapping("/category/show")
    public String show() {
        return "/category/list";
    }

    @GetMapping("/categories")
    @ResponseBody
    public R query(@RequestParam Map<String, Object> params) {
        List<Category> list = categoryService.query(params);
        return R.ok("返回数据列表", list, list.size(), null);
    }

    @GetMapping("/category/{pId}")
    public String showAddView(@PathVariable("pId") Integer pId, Model model) {
        // 判断分类是否为根分类
        if (pId == null && pId == 0) {
            model.addAttribute("pName", "根分类");
        } else {
            model.addAttribute("pName", categoryService.getById(pId).getName());
        }
        return "category/input?pId="+pId.toString();
    }

    @PostMapping("/category")
    @ResponseBody
    public R add(@RequestBody Category category) {
        int count = categoryService.add(category);
        if (count > 0) {
            return R.ok("添加成功", null, count, "/categories");
        } else {
            return R.error("添加失败", null, count, null);
        }
    }

    @GetMapping("/category/{id}")
    public String showUpdateView(@PathVariable("id") Integer id, Model model) {
        // 获取该分类实体
        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        // 获取父分类名称
        if (category.getParent_id() == null && category.getParent_id() == 0) {
            model.addAttribute("pName", "根分类");
        } else {
            model.addAttribute("pName", categoryService.getById(category.getParent_id()).getName());
        }
        return "category/edit?id="+id.toString();
    }

    @PutMapping("/category")
    @ResponseBody
    public R update(@RequestBody Category category) {
        int count = categoryService.update(category);
        if (count > 0) {
            return R.ok("修改成功", null, count, "/categories");
        } else {
            return R.error("修改失败", null, count, null);
        }
    }

    @DeleteMapping("/category/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        int count = categoryService.delete(id);
        if (count > 0) {
            return R.ok("删除成功", null, count, "/categories");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }

    @DeleteMapping("/category")
    @ResponseBody
    public R batchDelete(@RequestBody Integer[] ids) {
        int count = categoryService.batchDelete(ids);
        if (count > 0) {
            return R.ok("删除成功", null, count, "/categories");
        } else {
            return R.error("删除失败", null, count, null);
        }
    }
}
