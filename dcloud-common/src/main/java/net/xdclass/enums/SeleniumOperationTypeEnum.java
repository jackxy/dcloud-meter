package net.xdclass.enums;

public enum SeleniumOperationTypeEnum {

    /**
     * 打开窗口
     */
    BROWSER_OPEN,

    /**
     * 关闭窗口
     */
    BROWSER_CLOSE,

    /**
     * 切换窗口通过句柄
     */
    BROWSER_SWITCH_BY_HANDLER,

    /**
     * 切换窗口通过索引
     */
    BROWSER_SWITCH_BY_INDEX ,

    /**
     * 最大化窗口
     */
    BROWSER_MAXIMIZE,

    /**
     * 设置窗口大小
     */
    BROWSER_RESIZE,

    /**
     * 窗口刷新
     */
    BROWSER_REFRESH,

    /**
     * 设置窗口后退
     */
    BROWSER_BACK ,

    /**
     * 设置窗口前进
     */
    BROWSER_FORWARD,


    /**
     * 鼠标左键点击
     */
    MOUSE_LEFT_CLICK,

    /**
     * 鼠标右键右击
     */
    MOUSE_RIGHT_CLICK,

    /**
     * 鼠标左键双击
     */
    MOUSE_DOUBLE_CLICK,

    /**
     * 鼠标移入元素
     */
    MOUSE_MOVE_TO_ELEMENT,

    /**
     * 鼠标拖拽元素到目标元素
     */
    MOUSE_DRAG_ELEMENT_TO_ELEMENT,





    /**
     * 键盘输入
     */
    KEYBOARD_INPUT,

    /**
     * 表单提交
     */
    KEYBOARD_SUBMIT,


    /**
     * 清空
     */
    KEYBOARD_CLEAR,



    /**
     * 隐式等待
     */
    WAIT_HIDE,

    /**
     * 显式等待
     */
    WAIT_SHOW,
    /**
     * 强制等待
     */
    WAIT_FORCE,





    /**
     * 网页标题相等
     */
    ASSERTION_BROWSER_TITLE_EQUAL,

    /**
     * 网页标题不相等
     */
    ASSERTION_BROWSER_TITLE_NOT_EQUAL,

    /**
     * 网页标题包含
     */
    ASSERTION_BROWSER_TITLE_CONTAIN,

    /**
     * 网页标题不包含
     */
    ASSERTION_BROWSER_TITLE_NOT_CONTAIN,

    /**
     * 网页URL相等
     */
    ASSERTION_BROWSER_URL_EQUAL,

    /**
     * 网页URL不相等
     */
    ASSERTION_BROWSER_URL_NOT_EQUAL,

    /**
     * 网页URL包含
     */
    ASSERTION_BROWSER_URL_CONTAIN,

    /**
     * 网页URL不包含
     */
    ASSERTION_BROWSER_URL_NOT_CONTAIN,





    /**
     * 元素文本大于
     */
    ASSERTION_ELEMENT_TEXT_GREAT_THEN,

    /**
     * 元素文本小于
     */
    ASSERTION_ELEMENT_TEXT_LESS_THEN ,

    /**
     * 元素文本相等
     */
    ASSERTION_ELEMENT_TEXT_EQUAL,

    /**
     * 元素文本不相等
     */
    ASSERTION_ELEMENT_TEXT_NOT_EQUAL,

    /**
     * 元素文本包含
     */
    ASSERTION_ELEMENT_TEXT_CONTAIN,

    /**
     * 元素文本不包含
     */
    ASSERTION_ELEMENT_TEXT_NOT_CONTAIN,





    /**
     * 元素存在
     */
    ASSERTION_ELEMENT_EXIST,

    /**
     * 元素不存在
     */
    ASSERTION_ELEMENT_NOT_EXIST,

    /**
     * 元素启用
     */
    ASSERTION_ELEMENT_ENABLE,

    /**
     * 元素禁用
     */
    ASSERTION_ELEMENT_DISABLE,

    /**
     * 元素可见
     */
    ASSERTION_ELEMENT_VISIBLE,

    /**
     * 元素不可见
     */
    ASSERTION_ELEMENT_INVISIBLE,

    /**
     * 元素可选
     */
    ASSERTION_ELEMENT_SELECT,

    /**
     * 元素不可选
     */
    ASSERTION_ELEMENT_UNSELECT;



}
