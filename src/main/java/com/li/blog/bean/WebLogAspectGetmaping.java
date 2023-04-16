package com.li.blog.bean;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.li.blog.entity.po.Record;
import com.li.blog.service.ArticleService;
import com.li.blog.service.RecordService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName WebLogAspect
 * @Description TODO
 * @Author Nine
 * @Date 2023/4/16 20:08
 * @Version 1.0
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspectGetmaping {

    @Resource
    private RecordService recordService;

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void webLog() {
    }
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Date startTime =new Date(System.currentTimeMillis());
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String clientIP = ServletUtil.getClientIP(request, null);
        Record webLog = new Record();
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        Date endTime =new Date(System.currentTimeMillis());
        long between = DateUtil.between(startTime, endTime, DateUnit.SECOND);
        String urlStr = request.getRequestURL().toString();
        webLog.setApiUrl(StrUtil.removePrefix(urlStr,StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath())));
        webLog.setIp(clientIP);
        webLog.setTime(between);
        recordService.saveList(webLog);
        return result;
    }
}
