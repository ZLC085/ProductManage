package com.ttt.mim.common.utils.log;

import com.ttt.mim.common.utils.CookieUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 日志工具类
 */
class LogUtils {

    /**
     * @param joinPoint JoinPoint
     * @return 格式化后的用户日志内容
     * @Description 获取用户日志信息中的动态参数，然后替换
     */
    static String operationFormat(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        AddLogUser addLogUser = getAnnotationLog(joinPoint);

        //用户日志原始内容
        assert addLogUser != null;
        String operation = addLogUser.operation();

        //匹配{}
        String regValue = "(\\{).*(})";
        //匹配[]
        String regArray = "(\\[).*(])";

        // 如果匹配上了{},则把内容当作变量
        if (Pattern.compile(regValue).matcher(operation).find()) {
            //参数顺序序号
            int num = -1;
            Matcher matcher = Pattern.compile("\\d+").matcher(operation);
            while (matcher.find()) {
                num = Integer.parseInt(matcher.group());
            }

            // 如果匹配上了[],则把内容当作数组
            if (Pattern.compile(regArray).matcher(operation).find()) {
                return operation.replaceAll(
                        regValue,
                        Collections.singletonList(args[num - 1]).toString());
            } else {
                return operation.replaceAll(
                        regValue,
                        args[num - 1].toString());
            }
        } else {
            return operation;
        }
    }

    /**
     * @param joinPoint JoinPoint
     * @return AddLogUser
     * @Description 是否存在注解，如果存在就获取
     */
    private static AddLogUser getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(AddLogUser.class);
        }

        return null;
    }

    /**
     * @param joinPoint JoinPoint
     * @param e         Exception
     * @return 全类名.方法名(): Exception
     * @Description 生成系统日志
     */
    static String logSysMsg(JoinPoint joinPoint, Exception e) {
        Signature signature = joinPoint.getSignature();

        return signature.getDeclaringTypeName() + "." +
                signature.getName() + "(): " +
                e.getMessage();
    }

    /**
     * @param request HttpServletRequest
     * @return 当前登录用户ip
     * @Description 获取当前登录用户ip
     */
    static String getIpAddr(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    assert inet != null;
                    ipAddress = inet.getHostAddress();
                } else if (ipAddress.equals("0:0:0:0:0:0:0:1")) {
                    ipAddress = "127.0.0.1";
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }

        return ipAddress;
    }

    /**
     * @param request HttpServletRequest
     * @return Map<String, String> 用户名和用户id
     * @Description 通过cookie获取用户名和用户id
     */
    static Map<String, String> getUserNameAndUserId(HttpServletRequest request) {
        CookieUtils cookieUtils = new CookieUtils();
        Cookie[] cookies = cookieUtils.getCookies(request);

        Map<String, String> map = new HashMap<>();

        for (Cookie cookie : cookies) {
            map.put(cookie.getName(), cookie.getValue());
        }

        return map;
    }
}
