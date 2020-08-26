package com.ttt.mim.dao;

import com.ttt.mim.domain.PurchaseDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDetailsDao {
    PurchaseDetails get(String purchaseNum);
}
