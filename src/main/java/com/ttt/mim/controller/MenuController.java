package com.ttt.mim.controller;

import org.springframework.ui.Model;
import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.Menu;
import com.ttt.mim.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;
    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    /**
    *请求显示默认页面
     */
    @GetMapping("/menu/show")
    public  String show(){
        return "menu/list";
    }

    /**
     * 请求数据
     */
    @GetMapping("/menus")
    @ResponseBody
    public R query(@RequestParam Map<String,Object> param){
        List<Menu> list=menuService.query(param);
        int total=menuService.count();
        return R.ok("返回数据列表",list,total,null);
    }
    /**
     * 请求添加页面
     */
    @GetMapping("/menu")
    @ResponseBody
    public  String showAddView(){
        return "menu/input";
    }
    /**
     * 添加提交
     */
    @PostMapping("/menu")
    @ResponseBody
    public R add(@RequestBody Menu menu){
        int count=menuService.add(menu);
        if(count>0){
            return R.ok("添加成功",null,count,"/menus");
        }else {
            return R.error("添加失败",null,count,null);
        }
    }
    /**
     * 请求修改
     */
    @GetMapping("/menu/update/{id}")
    public String showUpdateView(@PathVariable("id") Integer id, Model model){
       Menu menu=menuService.getById(id);
       model.addAttribute("menu",menu);
       return  "menu/edit";
    }
   /**
    * 修改提交
    */
   @PutMapping("/menu/update")
   @ResponseBody
    public  R update(@RequestBody Menu menu){
       int count=menuService.update(menu);
       if(count>0){
           return R.ok("修改成功",null,count,"/menus");
       }else {
           return  R.error("修改失败",null,count,null);
       }
   }
    /**
     * 单个删除
     */
    @DeleteMapping("/menu/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id){
        int count=menuService.delete(id);
        if(count>0){
            return R.ok("删除成功",null,count,"/menus");
        }else {
            return  R.error("删除失败",null,count,null);
        }
    }
    /**
     * 多条删除
     */
    @DeleteMapping("/menu")
    @ResponseBody
    public R batchDelete(@RequestBody Integer[] ids){
        int count=menuService.batchDelete(ids);
        if(count>0){
            return R.ok("删除成功",null,count,"/menus");
        }else {
            return R.error("删除失败",null,count,null);
        }
    }
    /**
     * 查看详情
     */
    @GetMapping("/menu/detail/{id}")
    @ResponseBody
    public Menu query(@PathVariable("id") Integer id){
      return menuService.getById(id);
    }
}
