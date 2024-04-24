package net.xdclass.enums;

/**
 * Selenium操作枚举类，定义了不同种类的Selenium操作。
 */
public enum SeleniumOperationEnum {
    // 浏览器操作
    BROWSER,

    // 鼠标操作
    MOUSE,

    // 键盘操作
    KEYBOARD,

    // 等待操作
    WAIT,

    // 断言操作，一般用于验证页面状态或元素状态
    ASSERTION,

    // 针对整个浏览器页面的断言操作
    ASSERTION_BROWSER,

    // 针对页面元素的断言操作
    ASSERTION_ELEMENT,

    // 针对浏览器页面文本内容的断言操作
    ASSERTION_ELEMENT_TEXT,

    // 针对浏览器页面元素属性的断言操作
    //ASSERTION_BROWSER_ATTRIBUTE;


}
