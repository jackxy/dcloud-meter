package net.xdclass.enums;

import lombok.Getter;

public enum BizCodeEnum {

    /**
     * 文件操作相关
     */
    FILE_REMOTE_DOWNLOAD_FAILED(220404,"远程文件下载失败"),
    FILE_REMOTE_READ_FAILED(220403,"远程文件读取失败"),
    FILE_REMOTE_UPLOAD_FAILED(220407,"文件上传失败"),
    FILE_REMOTE_UPLOAD_IS_EMPTY(220408,"上传文件为空"),
    FILE_PRE_SIGNED_FAILED(220409,"临时url生成失败"),
    FILE_CREATE_TEMP_FAILED(220411,"生成临时文件失败"),

    /**
     * 压测相关
     */
    STRESS_MODULE_ID_NOT_EXIST(260001,"模块id不存在"),
    STRESS_CASE_ID_NOT_EXIST(260002,"压测用例id不存在"),
    STRESS_UNSUPPORTED(260005,"不支持的压测类型"),
    STRESS_ASSERTION_UNSUPPORTED_ACTION(260007, "不支持的断言动作"),
    STRESS_ASSERTION_UNSUPPORTED_FROM(260008, "不支持的断言来源"),

    /**
     * API操作
     */
    API_OPERATION_UNSUPPORTED_FROM(230004, "不支持的来源操作"),
    API_OPERATION_UNSUPPORTED_ASSERTION(230005, "不支持的断言操作"),
    API_OPERATION_UNSUPPORTED_RELATION(230006, "不支持的关联取值"),
    API_RELATION_NOT_EXIST(230007, "关联参数不存在"),
    API_ASSERTION_FAILED(230008, "API断言失败"),
    API_FILE_NOT_EXIST(230010, "API上传文件不存在"),
    API_CASE_STEP_IS_EMPTY(280404,"API用例步骤不存在" ),

    UI_UNSUPPORTED_BROWSER_DRIVER(401000, "暂不支持该浏览器"),
    UI_ELEMENT_NOT_EXIST(401404,"元素定位不存在" ),
    UI_OPERATION_UNSUPPORTED(401405,"不支持操作类型" ),
    UI_OPERATION_UNSUPPORTED_BROWSER(401406,"不支持浏览器操作" ),
    UI_OPERATION_UNSUPPORTED_MOUSE(401407,"不支持鼠标操作" ),
    UI_OPERATION_UNSUPPORTED_KEYBOARD(401408,"不支持键盘操作" ),
    UI_OPERATION_UNSUPPORTED_WAIT(401409,"不支持等待操作" ),
    UI_OPERATION_UNSUPPORTED_ASSERTION(401410,"不支持的断言类型" ),

    TEST_TYPE_UNSUPPORTED(504404,"未知的测试类型" );



    @Getter
    private String message;

    @Getter
    private int code;

    private BizCodeEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
}
