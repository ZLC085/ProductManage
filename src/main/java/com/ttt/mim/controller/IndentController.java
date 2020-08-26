package com.ttt.mim.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.Headers;
import com.ttt.mim.common.utils.R;
import com.ttt.mim.domain.Indent;
import com.ttt.mim.domain.IndentOutData;
import com.ttt.mim.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class IndentController {
    private final IndentService indentService;
    private final static String BaseString ="/indent";
    @Autowired
    public IndentController(IndentService indentService ) {
        this.indentService = indentService;
    }

    /**
     * 获取订单列表
     * @param map 组合条件信息
     */
    @GetMapping("/indents")
    @ResponseBody
    public R getIndents(@RequestParam  Map<String,Object> map){
        List<IndentOutData> indentOutData = indentService.query(map);
        return R.ok("查询成功", indentOutData, indentService.count(map), null);
    }

    /**
     * 获取订单信息完善/修改页面
     * @param indentNum 订单编号(如果indentNum为null或者空字符串，对应订单添加，否则订单修改)
     */
    @GetMapping(BaseString)
    public String getEditView(@RequestParam String indentNum,Model model){
        if(indentNum!=null && !indentNum.equals("")){
            Boolean flag=indentService.isRevisable(indentNum);
            if(flag){
                Indent indent=indentService.get(indentNum);
                model.addAttribute("indentEditInfo",indent);
            }
        }
        return BaseString+"/edit";
    }

    /**
     * 订单添加
     */
    @PostMapping(BaseString)
    @ResponseBody
    public R add(@RequestBody JSONObject jsonObject){
        int i = indentService.add(jsonObject);
        if (i<=0){
            return R.error("添加失败",null,0,null);
        }else {
            return R.ok("添加成功",null,i,null);
        }
    }

    /**
     * 订单修改
     * @param indent 订单数据
     */
    @PutMapping(BaseString)
    @ResponseBody
    public R update(@RequestBody Indent indent){
        int i=indentService.update(indent);
        if (i<=0){
            return R.error("修改失败",null,i,null);
        }else {
            return R.ok("修改成功",null,i,null);
        }
    }
    /**
     * 获取订单详情页面
     * @param indentNum 订单编号
     */
    @GetMapping(BaseString+"/{indentNum}")
    public String getDetailsView(@PathVariable("indentNum") String indentNum,Model model){
        JSONObject jsonObject= indentService.getDetails(indentNum);
        model.addAttribute("indentDetailsJsonData",jsonObject);
        System.out.println(jsonObject);
        return BaseString+"/show";
    }

    /**
     * 订单批量删除
     * @param indentNums 订单编号数组
     */
    @DeleteMapping(BaseString)
    @ResponseBody
    public R delete(@RequestBody String[] indentNums){
        int i = indentService.batchDelete(indentNums);
        if (i<=0){
            return R.error("删除失败");
        }else {
            return R.ok("删除成功");
        }
    }

    /**
     * 订单单个删除
     * @param indentNum 订单编号
     */
    @DeleteMapping(BaseString+"/{indentNum}")
    @ResponseBody
    public R delete(@PathVariable String indentNum){
        int i = indentService.delete(indentNum);
        if (i<=0){
            return R.error("删除失败",null,i,null);
        }else {
            return R.ok("删除成功",null,i,null);
        }
    }

    /**
     * 订单导出
     * @param jsonObject 数据
     */
    @PostMapping(BaseString+"/export")
    public ResponseEntity<byte[]> export(HttpServletRequest request, @RequestBody JSONObject jsonObject) throws IOException {
        String[] indentNums= new String[jsonObject.getJSONArray("indentNums").size()];
        indentNums = JSONObject.parseArray(jsonObject.getJSONArray("indentNums").toJSONString(), String.class).toArray(indentNums);

        String type=jsonObject.getString("type");
        Map<String,byte[]> map=null;
        if (type.equals("docx")){
            map = indentService.getAllDocx(request, indentNums);
            System.out.println(map);
        }else if (type.equals("xlsx")){
            map=indentService.getAllExcel(request, indentNums);
        }
        if(map==null)
            return null;
        return indentService.getResponseEntity(request, map);
    }

}
