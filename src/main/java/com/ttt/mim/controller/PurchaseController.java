package com.ttt.mim.controller;

import com.alibaba.fastjson.JSONObject;
import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.Purchase;
import com.ttt.mim.domain.PurchaseOutData;
import com.ttt.mim.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
public class PurchaseController {
    private final PurchaseService purchaseService;
    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }
    private final static String BaseString ="/purchase";

    /**
     * 获取采购单列表
     * @param map 组合条件
     */
    @GetMapping("/purchases")
    @ResponseBody
    public R getPurchases(@RequestParam Map<String,Object> map){
        List<PurchaseOutData> purchaseOutData = purchaseService.query(map);
        int count=purchaseService.count(map);
        return R.ok("查询成功", purchaseOutData, count,null);
    }

    /**
     * 获取采购单信息完善/修改页面
     * @param purchaseNum 采购单编号
     */
    @GetMapping(BaseString)
    public String getEditView(@RequestParam String purchaseNum,Model model){
        if(purchaseNum != null && !purchaseNum.equals("")){
            Purchase purchase=purchaseService.get(purchaseNum);
            model.addAttribute("EditPurchaseInfo", purchase);
        }
        return BaseString +"/edit";
    }

    /**
     * 采购单添加
     * @param jsonObject 数据
     */
    @PostMapping(BaseString)
    @ResponseBody
    public R add(@RequestBody JSONObject jsonObject){
        int i=purchaseService.add(jsonObject);
        if (i<=0){
            return R.error("添加失败",null,i,null);
        }else {
            return R.ok("添加成功",null,i,null);
        }
    }

    /**
     * 采购单修改
     * @param jsonObject 数据
     */
    @PutMapping(BaseString)
    @ResponseBody
    public R update(@RequestBody JSONObject jsonObject){
        int i=purchaseService.update(jsonObject);
        if (i<=0){
            return R.error("修改失败",null,i,null);
        }else {
            return R.ok("修改成功",null,i,null);
        }
    }

    /**
     * 获取采购单详情界面
     * @param purchaseNum 采购单编号
     */
    @GetMapping(BaseString+"/{purchaseNum}")
    public String getDetailsView(@PathVariable("purchaseNum") String purchaseNum,Model model){
        JSONObject details = purchaseService.getDetails(purchaseNum);
        model.addAttribute("purchaseDetails", details);
        System.out.println(details);
        return BaseString +"/show";
    }

    /**
     * 获取采购单审批界面
     * @param purchaseNum 采购单编号
     */
    @GetMapping(BaseString+"/approve/{purchaseNum}")
    public String getApproveView(@PathVariable("purchaseNum")String purchaseNum,Model model){
        Purchase purchase=purchaseService.get(purchaseNum);
        model.addAttribute("approvePurchaseInfo", purchase);
        return BaseString +"/approve";
    }

    /**
     * 采购单审批
     * @param purchase 数据
     */
    @PostMapping(BaseString+"/approve")
    @ResponseBody
    public R approve(@RequestBody Purchase purchase){
        int i=purchaseService.approve(purchase);
        if (i<=0){
            return R.error("审批失败",null,i,null);
        }else {
            return R.ok("审批成功",null,i,null);
        }
    }

    /**
     * 采购单批量删除
     * @param purchaseNums 采购单编号数组
     */
    @DeleteMapping(BaseString)
    @ResponseBody
    public R batchDelete(@RequestBody String[] purchaseNums){
        int i = purchaseService.batchDelete(purchaseNums);
        if (i<=0){
            return R.error("删除失败",null,i,null);
        }else {
            return R.ok("删除成功",null,i,null);
        }
    }

    /**
     * 采购单单个删除
     * @param purchaseNum 采购单编号
     */
    @DeleteMapping(BaseString+"/{purchaseNum}")
    @ResponseBody
    public R delete(@PathVariable String purchaseNum){
        int i = purchaseService.delete(purchaseNum);
        if (i<=0){
            return R.error("删除失败",null,i,null);
        }else {
            return R.ok("删除成功",null,i,null);
        }
    }










}
