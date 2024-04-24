package net.xdclass.service.ui;

import org.openqa.selenium.WebElement;

/**
 * Selenium鼠标操作服务接口，提供了一系列的鼠标操作方法，用于与网页元素进行交互。
 */
public interface SeleniumMouseOperationService {

    /**
     * 对指定的网页元素执行左键单击操作。
     *
     * @param webElement 要单击的网页元素。
     */
    void leftClick(WebElement webElement);

    /**
     * 对指定的网页元素执行右键单击操作。
     *
     * @param webElement 要单击的网页元素。
     */
    void rightClick(WebElement webElement);

    /**
     * 对指定的网页元素执行双击操作。
     *
     * @param webElement 要双击的网页元素。
     */
    void doubleClick(WebElement webElement);

    /**
     * 将源网页元素拖动到目标网页元素的位置。
     *
     * @param source 源网页元素。
     * @param target 目标网页元素。
     */
    void dragElementToElement(WebElement source, WebElement target);

    /**
     * 将鼠标移动到指定的网页元素上。
     *
     * @param webElement 要移动到的网页元素。
     */
    void moveToElement(WebElement webElement);
}

