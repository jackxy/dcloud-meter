package net.xdclass.service.ui;

import org.openqa.selenium.WebElement;

public interface SeleniumKeyboardOperationService {

    /**
     * 输入，可变参数
     * @param webElement
     * @param text
     */
    void input(WebElement webElement, String... text);

    /**
     * 清除
     * @param webElement
     */
    void clear(WebElement webElement);

    /**
     * 提交
     * @param webElement
     */
    void submit(WebElement webElement);
}
