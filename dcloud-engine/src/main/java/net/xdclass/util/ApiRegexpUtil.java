package net.xdclass.util;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class ApiRegexpUtil {
    /**
     * 正则表达式 - 关联取值
     * 匹配以 {{ 开头，以 }} 结尾，且中间不包含 }} 的字符串
     */
    public static final String REGEXP = "\\{\\{([^}]+)}}";

    /**
     * 正则表达式具名 - 关联取值
     * @param name
     * @return
     */
    public static String byName(String name) {
        return "\\{\\{(" + name + ")}}";
    }
}

