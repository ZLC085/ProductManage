package com.ttt.mim.controller;

import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.Price;
import com.ttt.mim.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class PriceController {
    private PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService){this.priceService = priceService;}

    @GetMapping("/price/show")
    public String show() {
        return "price/main/list";
    }

    @GetMapping("/price")
    public String showAddView() {
        return "price/main/input";
    }

    @PostMapping("/price")
    @ResponseBody
    public R add(@RequestBody Price price) {
        int count =priceService.add(price);
        if (count > 0) {
            return R.ok("添加成功",null,count,"/prices");
        } else {
            return R.error("添加失败",null,count,null);
        }
    }
    @DeleteMapping("/price/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        int count=priceService.delete(id);
        if (count > 0) {
            return R.ok("删除成功",null,count,"/prices");
        } else {
            return R.error("删除失败",null,count,null);
        }
    }
    @DeleteMapping("/price")
    @ResponseBody
    public R batchDelete(@RequestParam("ids[]") Integer[] ids) {
        int count =priceService.batchDelete(ids);
        if (count > 0) {
            return R.ok("删除成功",null,count,"/prices");
        } else {
            return R.error("删除失败",null,count,null);
        }
    }

    @GetMapping("/view{id}")
    @ResponseBody
    public Price priceDetail(@PathVariable("id") Integer id) {
        return priceService.priceDetail(id);
    }

    @GetMapping("/prices")
    @ResponseBody
    public List<Price> query(@RequestParam Map<String, Object> params) {
        return priceService.query(params);
    }

    @PutMapping("/price")
    @ResponseBody
    public R update(@RequestParam("price") Price price)
    {
        int count =priceService.update(price);
        if(count>0){
            return R.ok("修改成功",null,count,"/price");
        }else {
            return R.error("修改失败",null,count,null);
        }
    }
}
