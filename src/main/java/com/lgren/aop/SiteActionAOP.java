
package com.lgren.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SiteActionAOP {
    /*@Pointcut("execution(* com.lgren.controller..*(com.lgren.pojo..*))")
    public void paramObjectValidate(){}*/

    /*@Pointcut("execution(* com.lgren.controller..*(com.lgren.pojo..*))")
    public void test(){
        System.out.println("test111");
    }*/
/*    @Before("paramObjectValidate()")
    public void paramUserValidate(JoinPoint joinPoint) throws IllegalAccessException {
        Object[] params = joinPoint.getArgs();
        Object obj = params[0];
        if (obj != null){
            Class<?> objClass = obj.getClass();
            Field[] fields = objClass.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                Object value = f.get(obj);
                if ("".trim().equals(value) || "null".trim().equals(value)) {
                    f.set(obj,null);
                }
            }
        }
    }*/

/*    @Before("paramObjectValidate()")
    public void paramValidate(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        for (int i = 0;i<params.length;i++) {
            if ("".trim().equals(params[i])) {
                params[i] = null;
            }
        }
    }*/
}

