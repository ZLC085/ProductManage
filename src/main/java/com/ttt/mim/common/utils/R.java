package com.ttt.mim.common.utils;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {

    public R() {
        put("code", 200);
        put("msg", "操作成功");
    }

    public static R error() {
        return error(1, "服务器异常，操作失败");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }


    public static R ok(String msg, Object data, Object extdata, String redirect) {
        R r = new R();
        r.put("code", 200);
        r.put("msg", msg);
        r.put("data", data);
        r.put("extdata", extdata);
        r.put("redirect", redirect);
        return r;
    }


    public static R error(String msg, Object data, Object extdata, String redirect) {
        R r = new R();
        r.put("code", 500);
        r.put("msg", msg);
        r.put("data", data);
        r.put("extdata", extdata);
        r.put("redirect", redirect);
        return r;
    }



//    @Override
//    public R put(String key, Object value) {
//        super.put(key, value);
//        return this;
//    }
}
