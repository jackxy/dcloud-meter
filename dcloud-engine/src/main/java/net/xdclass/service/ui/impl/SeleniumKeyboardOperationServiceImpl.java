package net.xdclass.service.ui.impl;

import net.xdclass.service.ui.SeleniumKeyboardOperationService;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Component
public class SeleniumKeyboardOperationServiceImpl implements SeleniumKeyboardOperationService {
    @Override
    public void input(WebElement webElement, String... text) {
        webElement.sendKeys(text);
    }

    @Override
    public void clear(WebElement webElement) {
        webElement.clear();
    }

    @Override
    public void submit(WebElement webElement) {
        webElement.submit();
    }
}
