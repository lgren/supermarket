package com.lgren.util;

import java.lang.reflect.Field;

/**
 * 给一个变量赋值的函数,主要作用是给VO添加值
 */
public class SetValue {
    /**
     * 此方法为改变当前对象的变量
     * @param vo 需要赋值的对象
     * @param fieldName 赋值的字段名
     * @param value 需要赋的值,Object
     * @param <T> 赋值的类
     * @return 返回赋值的对象
     * User user = new User();<br/>
     * SetValue.getAimValue(user,"username","admin");<br/>
     * System.out.println(user.getUsername());//值为admin
     */
    public static  <T> T getAimValue(T vo,String fieldName,Object value) {
        Field f = null;
        try {
            f = vo.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            f.set(vo,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return vo;
    }
    public static  <T> T getSuperAimValue(T vo,String fieldName,Object value){
        Field f = null;
        try {
            f = vo.getClass().getSuperclass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            f.set(vo,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return vo;
    }
}
