package net.xdclass.service.ui.impl;

import net.xdclass.service.ui.SeleniumBrowserOperationService;
import net.xdclass.util.SeleniumWebdriverContext;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Component
public class SeleniumBrowserOperationServiceImpl implements SeleniumBrowserOperationService {
    @Override
    public void open(String url) {
        WebDriver webDriver = SeleniumWebdriverContext.get();
        webDriver.get(url);
    }

    @Override
    public void close() {
        WebDriver webDriver = SeleniumWebdriverContext.get();
        webDriver.close();

    }

    @Override
    public void back() {
        WebDriver webDriver = SeleniumWebdriverContext.get();
        webDriver.navigate().back();
    }

    @Override
    public void forward() {
        WebDriver webDriver = SeleniumWebdriverContext.get();
        webDriver.navigate().forward();
    }

    @Override
    public void refresh() {
        WebDriver webDriver = SeleniumWebdriverContext.get();
        webDriver.navigate().refresh();

    }

    @Override
    public void switchByHandle(String handler) {
        WebDriver webDriver = SeleniumWebdriverContext.get();
        webDriver.switchTo().window(handler);
    }

    @Override
    public void switchByIndex(int index) {
        WebDriver webDriver = SeleniumWebdriverContext.get();
        Set<String> windowHandles = webDriver.getWindowHandles();
        String[] array = windowHandles.toArray(new String[]{});
        webDriver.switchTo().window(array[index]);
    }

    @Override
    public void resizeMaximize() {
        WebDriver webDriver = SeleniumWebdriverContext.get();
        webDriver.manage().window().maximize();

    }

    @Override
    public void resize(int x, int y) {
        WebDriver webDriver = SeleniumWebdriverContext.get();
        webDriver.manage().window().setSize(new Dimension(x,y));

    }
}
