package net.xdclass.service.ui.impl;

import net.xdclass.service.ui.SeleniumMouseOperationService;
import net.xdclass.util.SeleniumWebdriverContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
public class SeleniumMouseOperationServiceImpl implements SeleniumMouseOperationService {
    @Override
    public void leftClick(WebElement webElement) {
        webElement.click();
    }

    @Override
    public void rightClick(WebElement webElement) {
        Actions actions = new Actions(SeleniumWebdriverContext.get());
        actions.contextClick(webElement).perform();
    }

    @Override
    public void doubleClick(WebElement webElement) {
        Actions actions = new Actions(SeleniumWebdriverContext.get());
        actions.doubleClick(webElement).perform();

    }

    @Override
    public void dragElementToElement(WebElement source, WebElement target) {
        Actions actions = new Actions(SeleniumWebdriverContext.get());
        actions.dragAndDrop(source,target).perform();
    }

    @Override
    public void moveToElement(WebElement webElement) {
        Actions actions = new Actions(SeleniumWebdriverContext.get());
        actions.moveToElement(webElement).perform();
    }
}
