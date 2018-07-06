package com.leepon.cloud.advice;

import com.leepon.cloud.annotation.EncryptField;
import com.leepon.cloud.util.Helper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice(basePackages = "com.leepon.cloud.controller")
public class MyResponseBodyAdvice implements ResponseBodyAdvice {

    /**
     * 包含项
     */
    private String[] includes = {};
    /**
     * 是否加密
     */
    private boolean encrypt = true;

    @Override
    public boolean supports(MethodParameter methodParameter, Class clazz) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class clazz, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        //判断返回的对象类型
        if (o == null) {
            return null;
        }
        if (methodParameter.getMethod().isAnnotationPresent(EncryptField.class)) {
            //获取注解配置的需要处理的字段及是否需要加密
            EncryptField encryptField = methodParameter.getMethodAnnotation(EncryptField.class);
            includes = encryptField.includes();
            //是否加密
            encrypt = encryptField.encrypt();
        }

        Object retObj;
        if (o instanceof List) {
            List list = (List) o;
            retObj = handleList(list);
        } else {
            retObj = handleSingleObject(o);
        }
        return retObj;
    }

    /**
     * 处理返回值是单个enity对象
     *
     * @param o
     * @return
     */
    private Object handleSingleObject(Object o) {
        Map<String, Object> map = new HashMap<>();

        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object val;
            try {
                field.setAccessible(true);
                val = field.get(o);
                //如果未配置表示全部字段加密
                if (includes.length == 0) {
                    String newVal = Helper.encode(val.toString());
                    map.put(field.getName(), newVal);
                } else if (includes.length > 0) {

                    if (encrypt == true) {
                        //有限考虑包含字段是否加密
                        if (Helper.isStringInArray(field.getName(), includes)) {
                            String newVal = Helper.encode(val.toString());
                            map.put(field.getName(), newVal);
                        } else if (!isDeclaredFieldType(field)) {
                            //处理自定义class类
                            Field[] fields1 = val.getClass().getDeclaredFields();
                            for (Field field1 : fields1) {
                                field1.setAccessible(true);
                                Object val1 = field1.get(val);
                                Object newVal;
                                if (Helper.isStringInArray(field1.getName(), includes)) {
                                    newVal = Helper.encode(val1.toString());
                                } else {
                                    newVal = val1;
                                }
                                field1.set(val, newVal);
                            }
                            Object newVal = val;
                            map.put(field.getName(), newVal);
                        } else {
                            Object newVal = val;
                            map.put(field.getName(), newVal);
                        }
                    } else {
                        if (Helper.isStringInArray(field.getName(), includes)) {
                            Object newVal = val;
                            map.put(field.getName(), newVal);
                        } else {
                            String newVal = Helper.encode(val.toString());
                            map.put(field.getName(), newVal);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 处理返回值是列表
     *
     * @param list
     * @return
     */
    private List handleList(List list) {
        List retList = new ArrayList();
        for (Object o : list) {
            Map map = (Map) handleSingleObject(o);
            retList.add(map);
        }
        return retList;
    }

    public static boolean isDeclaredFieldType(Field field) {

        boolean flag = false;

        field.setAccessible(true);

        // 得到field的class
        Class fieldClazz = field.getType();

        //判断是否为基本类型
        if (fieldClazz.isPrimitive()) {
            flag = true;
        }
        //getName()返回field的类型全路径；
        if (fieldClazz.getName().startsWith("java.lang")) {
            flag = true;
        }
        return flag;
    }

}
