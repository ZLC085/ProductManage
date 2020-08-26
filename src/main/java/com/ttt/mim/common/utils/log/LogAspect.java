package com.ttt.mim.common.utils.log;

import com.ttt.mim.domain.LogSys;
import com.ttt.mim.domain.LogUser;
import com.ttt.mim.service.LogSysService;
import com.ttt.mim.service.LogUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.ttt.mim.common.utils.log.LogUtils.*;

/**
 * @Description 日志切面类
 */
@Aspect
@Component
public class LogAspect {

    private final LogUserService logUserService;

    private final LogSysService logSysService;

    private HttpServletRequest httpServletRequest;

    @Autowired
    public LogAspect(LogUserService logUserService, LogSysService logSysService, HttpServletRequest httpServletRequest) {
        this.logUserService = logUserService;
        this.logSysService = logSysService;
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @param joinPoint JoinPoint
     * @param exception Exception
     * @Description 添加系统日志
     */
    @AfterThrowing(value = "execution(public * com.ttt.mim.controller.*.*(..)))", throwing = "exception")
    public void logSysAdd(JoinPoint joinPoint, Exception exception) {
        LogSys logSys = new LogSys();
        logSys.setCreateTime(new Date());
        logSys.setMsg(logSysMsg(joinPoint, exception));

        logSysService.add(logSys);
    }

    /**
     * @param joinPoint JoinPoint
     * @Description 添加用户日志
     */
    @AfterReturning(pointcut = "@annotation(com.ttt.mim.common.utils.log.AddLogUser)")
    public void logUserAdd(JoinPoint joinPoint) {
        LogUser logUser = new LogUser();

        int userId = Integer.parseInt(getUserNameAndUserId(httpServletRequest).getOrDefault("userId", "0"));
        String userName = getUserNameAndUserId(httpServletRequest).getOrDefault("userName", "");

        logUser.setUserId(userId);
        logUser.setIp(getIpAddr(httpServletRequest));
        logUser.setOperation(operationFormat(joinPoint));
        logUser.setUserName(userName);
        logUser.setCreateTime(new Date());

        logUserService.add(logUser);
    }
}
