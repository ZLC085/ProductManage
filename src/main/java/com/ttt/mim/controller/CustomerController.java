package com.ttt.mim.controller;


import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.Customer;
import com.ttt.mim.domain.CustomerList;
import com.ttt.mim.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 客户管理
 * @author JY
 */
@Controller
//@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService=customerService;
    }

    /**
     * 客户添加页面
     * @return
     */
    @GetMapping("/customer")
    public String showAddView(){
        return "/customer/input";
    }

    /**
     * 添加提交
     * @param customer
     * @return
     */
    @PostMapping("/customer")
    @ResponseBody
    public R add(@RequestBody Customer customer){
        int count = customerService.add(customer);
        if (count > 0){
            return R.ok("添加成功",null,count,"/customers");
        }else {
            return R.error("添加失败",null,count,null);
        }
    }

    /**
     * 修改客户信息页面
     * @return
     */
    @GetMapping("/customer/{id}")
    public String showUpdateView(@PathVariable("id") Integer id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/customer/edit";
    }

    /**
     * 修改提交
     * @param customer
     * @return
     */
    @PutMapping("/customer")
    @ResponseBody
    public R update(@RequestBody Customer customer){
        int count = customerService.update(customer);
        if (count > 0){
            return R.ok("修改成功",null,count,"/customers");
        }else {
            return R.error("修改失败",null,count,null);
        }
    }

    /**
     * 删除提交
     * @param id
     * @return
     */
    @DeleteMapping("/customer/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id){
        int count = customerService.delete(id);
        if (count > 0){
            return R.ok("删除成功",null,count,"/customers");
        }else {
            return R.error("删除失败",null,count,null);
        }
    }

    /**
     * 批量删除提交
     * @param ids
     * @return
     */
    @DeleteMapping("/customer")
    @ResponseBody
    public R batchDelete(@RequestBody Integer[] ids){
        int count = customerService.batchDelete(ids);
        if (count > 0){
            return R.ok("删除成功",null,count,"/customers");
        }else {
            return R.error("删除失败",null,count,null);
        }
    }

    /**
     * 客户信息详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/customers/{id}")
    @ResponseBody
    public String findById(@PathVariable("id") Integer id,Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/customer/detail";
    }

    /**
     * 客户信息列表
     * @param params
     * @return
     */
    @GetMapping("/customers")
    @ResponseBody
    public R query(@RequestParam Map<String,Object> params){
        List<CustomerList> customerList = customerService.query(params);
        return R.ok("返回数据列表" ,customerList,customerService.count(params),null);
    }

}
