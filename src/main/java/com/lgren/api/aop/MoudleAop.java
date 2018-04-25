package com.lgren.api.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MoudleAop {
    //moudle中的参数
    @Pointcut("execution(* com.lgren.api.moudle..*(com.lgren.pojo.*.*))")
    public void getMoudleAllMethod() {}

    @Before("getMoudleAllMethod()")
    public void paramsVerification(JoinPoint joinPoint) {
        System.out.println("MoudleAop.paramsVerification");
        /*Object obj = joinPoint.getArgs()[0];
        if (obj == null) {
            obj = ((MethodSignature) joinPoint.getSignature()).getReturnType().newInstance();
        }*/
        /*MethodSignature method = (MethodSignature) joinPoint.getSignature();
        System.out.println(method.getReturnType());
        System.out.println(joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("getKind->" + joinPoint.getKind());
        System.out.println("getSignature->" + joinPoint.getSignature());*/

    }
}
