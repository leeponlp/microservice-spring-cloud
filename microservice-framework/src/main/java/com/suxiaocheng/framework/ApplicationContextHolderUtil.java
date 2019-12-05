package com.suxiaocheng.framework;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

//@Service
public class ApplicationContextHolderUtil implements ApplicationContextAware, DisposableBean {
    private static ApplicationContext applicationContext;

    //实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextHolderUtil.applicationContext = applicationContext;
    }

    //取得存储在静态变量中的ApplicationContext.
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    //从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    //从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        @SuppressWarnings("rawtypes")
        Map beanMaps = applicationContext.getBeansOfType(clazz);
        if (beanMaps != null && !beanMaps.isEmpty()) {
            return (T) beanMaps.values().iterator().next();
        } else {
            return null;
        }
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }

    @Override
    public void destroy() throws Exception {
        ApplicationContextHolderUtil.clearHolder();
    }

    public static void clearHolder() {
        applicationContext = null;
    }

    //获取applicationContext
    public static ApplicationContext get() {
        return applicationContext;
    }
}
