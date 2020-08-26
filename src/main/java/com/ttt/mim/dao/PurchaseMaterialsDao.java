package com.ttt.mim.dao;

import com.ttt.mim.domain.PurchaseDetails;
import com.ttt.mim.domain.PurchaseMaterials;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PurchaseMaterialsDao {
    List<PurchaseMaterials> get(String purchaseNum);
}
