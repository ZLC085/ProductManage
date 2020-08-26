package com.ttt.mim.common.utils;

/**
 * 检查数据是否合法
 * @author Abor
 */
public class CheckTools {
    public static Boolean checkNullOrEmpty(Object... objects){
        for (Object o:objects){
            if(o == null || o.toString().trim().equals("")){
                return false;
            }
        }
        return true;
    }
}
