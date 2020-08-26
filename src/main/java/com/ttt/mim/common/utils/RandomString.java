package com.ttt.mim.common.utils;

import java.util.Random;

public class RandomString {
    public static String getRandomStr(int length){
        String BaseString="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789";
        String resStr="";
        Random r=new Random(System.currentTimeMillis());
        for (int i=0;i<length;i++){
            resStr += BaseString.charAt(r.nextInt(BaseString.length()));
        }
        return resStr;
    }
}
