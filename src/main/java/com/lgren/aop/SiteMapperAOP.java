/*
package com.lgren.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;

*/
/**
 * 给pojo下方所有的类进行时间戳的操作
 *//*

@Aspect
@Component
public class SiteMapperAOP {
    @Pointcut("execution(* com.lgren.dao..insert(com.lgren.pojo..*)) || execution(* com.lgren.dao..insertSelective(com.lgren.pojo..*))")
    public void siteAddCreateTime(){}
    @Pointcut("execution(* com.lgren.dao..*(com.lgren.pojo..*))")
    public void siteAddModifiedTime(){}

*/
/*    @Before("siteAddModifiedTime()")
    public void siteAddModifiedTime(JoinPoint joinPoint) throws NoSuchFieldException, IllegalAccessException {
        Field f = joinPoint.getArgs()[0].getClass().getDeclaredField("gmtModified");
        f.setAccessible(true);
        f.set(joinPoint.getArgs()[0],new Date(System.currentTimeMillis()));
    }
    @Before("siteAddCreateTime()")
    public void siteAddCreateTime(JoinPoint joinPoint) throws NoSuchFieldException, IllegalAccessException {
        Field f = joinPoint.getArgs()[0].getClass().getDeclaredField("gmtCreate");
        f.setAccessible(true);
        f.set(joinPoint.getArgs()[0],new Date(System.currentTimeMillis()));
    }*//*


}
*/
