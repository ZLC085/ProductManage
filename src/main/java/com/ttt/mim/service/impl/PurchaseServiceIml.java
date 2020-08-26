package com.ttt.mim.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ttt.mim.common.exception.BusinessException;
import com.ttt.mim.dao.*;
import com.ttt.mim.domain.*;
import com.ttt.mim.service.PurchaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service("purchaseService")
public class PurchaseServiceIml implements PurchaseService {
    private final PurchaseDao purchaseDao;
    private final PurchaseDetailDao purchaseDetailDao;
    private final FactoryDao factoryDao;
    private final PurchaseOutDataDao purchaseOutDataDao;
    private final PurchaseDetailsDao purchaseDetailsDao;
    private final PurchaseMaterialsDao purchaseMaterialsDao;
    private final Logger logger=Logger.getLogger(this.getClass());

    @Autowired
    public PurchaseServiceIml(PurchaseDao purchaseDao, PurchaseDetailDao purchaseDetailDao, FactoryDao factoryDao, PurchaseOutDataDao purchaseOutDataDao, PurchaseDetailsDao purchaseDetailsDao, PurchaseMaterialsDao purchaseMaterialsDao) {
        this.purchaseDao = purchaseDao;
        this.purchaseDetailDao = purchaseDetailDao;
        this.factoryDao = factoryDao;
        this.purchaseOutDataDao = purchaseOutDataDao;
        this.purchaseDetailsDao = purchaseDetailsDao;
        this.purchaseMaterialsDao = purchaseMaterialsDao;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(JSONObject jsonObject) {
        Map<Purchase, List<PurchaseDetail>> map=convertJsonData(jsonObject);
        int affectedRows=add(map);
        return affectedRows;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add( Map<Purchase, List<PurchaseDetail>> map){
        int affectedRows=0;
        for (Purchase purchase:map.keySet()){
            purchase.setPurchaseNum(getAvailablePurchaseNum());
            purchase.setResult((byte) 0);
            if(!purchase.addCheckData()){
                logger.error("采购单数据校验不通过:"+purchase);
                throw new BusinessException("采购单数据检验不通过!");
            }
            for (PurchaseDetail detail : map.get(purchase)){
                detail.setPId(purchase.getPurchaseNum());
                if(!detail.addCheckData()){
                    logger.error("采购单细节数据校验不通过:"+detail);
                    throw new BusinessException("采购单细节数据检验不通过!");
                }
            }
            affectedRows += purchaseDao.add(purchase);
            affectedRows += purchaseDetailDao.batchAdd(map.get(purchase));
        }
        return affectedRows;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(JSONObject jsonObject) {
        int affectedRows=0;
        Map<Purchase,List<PurchaseDetail>> map=convertJsonData(jsonObject);
        Purchase dbPurchase=null;
        for (Purchase purchase:map.keySet()){
//            System.out.println("db: "+dbPurchase);
//            System.out.println("not db: "+purchase);
            if(dbPurchase==null){
                dbPurchase=purchaseDao.get(purchase.getPurchaseNum());
//                System.out.println("get...");
                if (dbPurchase.getIndentNum()!=null){
                    logger.warn("抱歉，订单附属采购单不可编辑！");
                    return 0;
                }
            }
            if(!dbPurchase.getFactoryId().equals(purchase.getFactoryId())){
//                System.out.println("continue...");
                continue;
            }

            affectedRows += purchaseDetailDao.deleteByPurchaseNum(purchase.getPurchaseNum());
            purchase.setResult((byte) 0);
            purchase.setApprovalId(null);
            purchase.setApprovalTime(null);
            purchase.setOpinion(null);
            affectedRows += purchaseDao.update(purchase);
            for (PurchaseDetail purchaseDetail:map.get(purchase)){
                purchaseDetail.setPId(purchase.getPurchaseNum());
            }
            affectedRows += purchaseDetailDao.batchAdd(map.get(purchase));
        }
        return affectedRows;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(String purchaseNum) {
        if(!isRevisable(purchaseNum)){
            return 0;
        }
        Purchase dbPurchase = get(purchaseNum);
        if(dbPurchase.getIndentNum()!=null){
            logger.warn("抱歉，订单附属采购单不可编辑！");
            return 0;

        }
        int affectedRows=0;
        affectedRows += purchaseDetailDao.deleteByPurchaseNum(purchaseNum);
        affectedRows += purchaseDao.delete(purchaseNum);
        return affectedRows;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(String[] purchaseNums) {
        int affectedRows=0;
        for (String num :purchaseNums){
            if(!isRevisable(num)){
                return 0;
            }
            Purchase dbPurchase = get(num);
            if(dbPurchase.getIndentNum()!=null){
                logger.warn("抱歉，订单附属采购单不可编辑！");
                return 0;
            }
        }
        for (String num : purchaseNums){
            affectedRows += purchaseDetailDao.deleteByPurchaseNum(num);
            affectedRows += purchaseDao.delete(num);
        }
        return affectedRows;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteByIndentNum(String indentNum) {
        int affectedRows=0;
        List<String> purchaseNums=purchaseDao.getPurchaseNumsByIndentNum(indentNum);
        if(purchaseNums==null || purchaseNums.size()==0){
            return 0;
        }
        for (String str : purchaseNums){
            if(!isRevisable(str)){
                return 0;
            }
        }
        for (String num : purchaseNums){
            affectedRows += purchaseDetailDao.deleteByPurchaseNum(num);
        }
        affectedRows += purchaseDao.deleteByIndentNum(indentNum);
        return affectedRows;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int approve(Purchase purchase) {
        int affectedRows=0;
        Byte flag=purchaseDao.getResultByPurchaseNum(purchase.getPurchaseNum());
        if(flag!=0){
            return 0;
        }
        purchase.setApprovalTime(new Date());
        affectedRows += purchaseDao.approve(purchase);
        return affectedRows;
    }

    @Override
    public Purchase get(String purchaseNum) {
        Purchase purchase=purchaseDao.get(purchaseNum);
        return purchase;
    }

    @Override
    public List<PurchaseOutData> query(Map<String, Object> map) {
        return purchaseOutDataDao.query(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return purchaseOutDataDao.count(map);
    }

    @Override
    public JSONObject getDetails(String purchaseNum) {
        JSONObject jsonObject=new JSONObject();
        PurchaseDetails purchaseDetails=purchaseDetailsDao.get(purchaseNum);
        List<PurchaseMaterials> purchaseMaterialsList=purchaseMaterialsDao.get(purchaseNum);
        jsonObject.put("purchaseDetails", purchaseDetails);
        jsonObject.put("purchaseMaterials",purchaseMaterialsList);
        return jsonObject;
    }

    @Override
    public Boolean isRevisable(String purchaseNum) {
        Byte flag=purchaseDao.getResultByPurchaseNum(purchaseNum);
        if(flag==null|| flag == (byte)1){
            return false;
        }
        return true;
    }

    @Override
    public String getAvailablePurchaseNum() {
        LocalDate now=LocalDate.now();
        String month=String.format("%02d", now.getMonthValue());
        String day=String.format("%02d", now.getDayOfMonth());
        String todayNumStr=now.getYear()+month+day;
        System.out.println(todayNumStr);
        String todayMaxPurchaseNum=purchaseDao.getTodayMaxPurchaseNum(todayNumStr);
        System.out.println(todayMaxPurchaseNum);
        int availableCount=1;
        if(todayMaxPurchaseNum==null){
            return todayNumStr+String.format("%07d", availableCount);
        }
        availableCount=Integer.valueOf(todayMaxPurchaseNum.substring(8, 15));
        if(availableCount<9999999){
            availableCount++;
        }else {
            return null;
        }
        String availablePurchaseNum=todayMaxPurchaseNum.substring(0,8)+String.format("%07d", availableCount);
        return availablePurchaseNum;
    }

    @Override
    public Map<Purchase, List<PurchaseDetail>> convertJsonData(JSONObject jsonObject) {
        Map<Purchase,List<PurchaseDetail>> map=new HashMap<>();
        Map<Integer,Purchase> mapFactoryIdIndex=new HashMap<>();

        String name=null;
        String remark=null;
        String indentNum=null;
        String purchaseNum=null;
        Byte result=null;
        JSONObject jsonPurchase=jsonObject.getJSONObject("purchase");

        if(jsonPurchase!=null){
            name=jsonPurchase.getString("name");
            remark=jsonPurchase.getString("remark");
            indentNum=jsonPurchase.getString("indentNum");
            purchaseNum=jsonPurchase.getString("purchaseNum");
            result=jsonPurchase.getByte("result");
        }

        JSONArray mArray=JSONObject.parseArray(jsonObject.get("materials").toString());
        List<JSONObject> jsonMsList=new ArrayList<>();
        for (Object o:mArray){
            jsonMsList.add(JSONObject.parseObject(o.toString()));
        }
        int count=0;
        for (JSONObject object:jsonMsList){
            Integer mId=object.getInteger("mId");
            Integer factoryId=object.getInteger("factoryId");
            Integer costId=object.getInteger("mcId");
            Double price=object.getDouble("price");
            Integer number=object.getInteger("number");
            Double sum=price*number;
            if(mapFactoryIdIndex.containsKey(factoryId)){
                Purchase purchase=mapFactoryIdIndex.get(factoryId);
                purchase.setSum(purchase.getSum()+sum);
                PurchaseDetail purchaseDetail=new PurchaseDetail(costId, number, sum);
                map.get(purchase).add(purchaseDetail);
            }else {
                Purchase purchase=new Purchase(null, indentNum, purchaseNum, name, remark, sum, factoryId, new Date(), new Date(), null, null, null, null);
                List<PurchaseDetail> purchaseDetailList=new ArrayList<>();
                PurchaseDetail purchaseDetail=new PurchaseDetail(costId, number, sum);
                purchaseDetailList.add(purchaseDetail);
                mapFactoryIdIndex.put(factoryId, purchase);
                map.put(purchase, purchaseDetailList);
            }
        }
        return map;
    }
}
