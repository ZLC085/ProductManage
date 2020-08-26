package com.ttt.mim.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    /**
     * @param request HttpServletRequest
     * @Description 读取所有cookie
     */
    public Cookie[] getCookies(HttpServletRequest request) {
        return request.getCookies();
    }

    /**
     * @param response HttpServletResponse
     * @param name     cookie name
     * @param value    cookie value
     * @Description 添加cookie
     */
    public void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name.trim(), value.trim());
        cookie.setMaxAge(30 * 60);// 设置为30min
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param name     cookie name
     * @Description 删除cookie
     */
    public void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();

        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
}
