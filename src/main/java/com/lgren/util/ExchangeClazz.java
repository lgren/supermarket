package com.lgren.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExchangeClazz {
    /**
     * 作用:将一个类,转换成另一个类<br/>
     * 具体实现:目标(target)对象中与目的(aim)类中<b>字段名一样时<b/>,目的类<b>对应字段的值<b/>设为<b>目标对象的值</b><br/>
     * 主要用途如:PO类传换成DTO或者VO类,DTO类转换成VO类,之后进一步对生成目的类后的对象进行进一步赋值等操作<br/>
     * 使用例如: User(id,用户名,密码) UserVO(id,用户名,其他)<br/>
     * User user = new User("132","lgren","password");<br/>
     * UserVO userVO = ExchangeClazz.getAimClazz(user,UserVO.class);<br/>
     * userVO中id = "132", 用户名 = "lgren", 其他 = null;<br/>
     * 注意 : 可以修改aimClazz的父类,但是读取不了target的父类
     *
     * @param target   目标 需要改换的类对象
     * @param aimClazz 目的 需要改成的类
     */
    //T代表:目标.任意类,如PO类等，V代表:目的,需要改成的类
    public static <T, V> V getAimClazz(T target, Class<V> aimClazz) {
        if (target == null) {
            throw new RuntimeException("ExchangeClazz类参数target为空");
        }
        //获取aim的全部属性值
        Field[] aimFields = aimClazz.getDeclaredFields();
        //获取aim父类的全部属性值
        Field[] aimSuperFields = aimClazz.getSuperclass().getDeclaredFields();
        //获取target的全部属性名
        Field[] targetFields = target.getClass().getDeclaredFields();
        //并创建一个aim类对象
        V aim = null;
        try {
            aim = aimClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //获取目标对象以及其父类的字段
        List<String> aimFieldsList = new ArrayList<String>();
        for (Field field : targetFields) {
            aimFieldsList.add(field.getName());
        }
        for(int i = 0; i<(aimFields.length+aimSuperFields.length) ; i++) {
            Field field = null;
            //获取aim里面的写方法 PropertyDescriptor(属性描述符)
            PropertyDescriptor aimPropDesc = null;
            try {
                if (i < aimFields.length){
                    field = aimFields[i];
                    aimPropDesc = new PropertyDescriptor(field.getName(), aimClazz);
                } else {
                    field = aimSuperFields[i - aimFields.length];
                    aimPropDesc = new PropertyDescriptor(field.getName(), aimClazz);
                }
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
            Method aimWriteMethod = aimPropDesc.getWriteMethod();
            //获取target里面的读方法
            //如果target里面存在aim类里面的字段值，就会自动copy
            if (aimFieldsList.contains(field.getName())) {
                PropertyDescriptor targetPropDesc = null;
                try {
                    targetPropDesc = new PropertyDescriptor(field.getName(), target.getClass());
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                }
                Method targetReadMethod = targetPropDesc.getReadMethod();
                try {
                    aimWriteMethod.invoke(aim, targetReadMethod.invoke(target));//invoke()?
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        //返回一个目的类(aim)
        return aim;
    }

    public static <T, V> List<V> getAimClazzList(List<T> targetList, Class<V> aimClazz) {
        if (targetList == null ){
            throw new RuntimeException("ExchangeClazz类参数targetList为空");
        }
        if (targetList.size() == 0) {
            return null;
        }
        List<V> vList = new ArrayList<>();
        for (T t : targetList) {
            vList.add(getAimClazz(t,aimClazz));
        }
        return vList;
    }
}
