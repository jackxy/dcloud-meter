package net.xdclass.enums;

public enum SeleniumByEnum {
    // 代表元素的唯一标识符，通常在网页的HTML代码中通过"id"属性指定。
    ID,

    // 代表元素的名称，通常在网页的HTML代码中通过"name"属性指定。
    NAME,

    // 代表元素的类名，通常在网页的HTML代码中通过"class"属性指定。
    CLASS_NAME,

    // 代表元素的CSS选择器，用于通过CSS规则来定位元素。
    CSS_SELECTOR,

    // 代表元素的链接文本，用于定位包含特定文本的链接元素。
    LINK_TEXT,

    // 代表元素的部分链接文本，用于定位包含特定子串的链接元素。
    PARTIAL_LINK_TEXT,

    // 代表元素的XPath表达式，用于通过XPath语法规则来定位元素。
    XPATH,

    // 代表元素的标签名，例如"div"、"p"等，用于定位特定标签类型的元素。
    TAG_NAME;

}
