package net.xdclass.util;

import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.Objects;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class SpringContextHolder {
    /**
     * Spring的ApplicationContext对象，用于管理所有的Bean对象
     */
    private static ApplicationContext applicationContext;

    /**
     * 设置ApplicationContext对象
     * @param applicationContext ApplicationContext对象
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 根据Bean的名称获取Bean对象
     * @param name Bean的名称
     * @return Bean对象
     */
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    /**
     * 根据Bean的类型获取所有匹配的Bean对象的Map集合
     * @param clazz Bean的类型
     * @return 匹配的Bean对象的Map集合
     */
    public static <T> Map<String,T> getBeans(Class<T> clazz){
        return applicationContext.getBeansOfType(clazz);
    }

    /**
     * 根据Bean的类型获取第一个匹配的Bean对象
     * @param clazz Bean的类型
     * @return 匹配的Bean对象
     */
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

}
