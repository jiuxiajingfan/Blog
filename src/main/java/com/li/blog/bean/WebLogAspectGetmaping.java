package com.li.blog.bean;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.li.blog.entity.po.Record;
import com.li.blog.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


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

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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
        //记录IP算作一个访问
        log.info("{}",clientIP.hashCode());
        redisTemplate.opsForValue().setBit("Guest",clientIP.hashCode()& Integer.MAX_VALUE, true);
        webLog.setTime(between);
        recordService.saveList(webLog);
        return result;
    }
}
