package com.ttt.mim.dao;

import com.ttt.mim.domain.PurchaseOutData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PurchaseOutDataDao {
    List<PurchaseOutData> query(Map<String,Object> map);
    PurchaseOutData get(String purchaseNum);
    int count(Map<String,Object> map);
}
