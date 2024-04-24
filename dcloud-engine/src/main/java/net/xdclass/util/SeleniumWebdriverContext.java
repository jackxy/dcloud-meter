package net.xdclass.util;

import org.openqa.selenium.WebDriver;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class SeleniumWebdriverContext {

    private static final ThreadLocal<WebDriver> THREAD_LOCAL = new ThreadLocal<>();

    public static WebDriver get() {
        return THREAD_LOCAL.get();
    }

    public static void  set(WebDriver webDriver){
        THREAD_LOCAL.set(webDriver);
    }

    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
