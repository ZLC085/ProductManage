package com.ttt.mim.dao;

import com.ttt.mim.domain.IndentDetailData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IndentDetailDao {
//    List<IndentDetailData> query(Map<String,Object> map);
    List<IndentDetailData> getByIndentNum(String indentNum);
}
