package net.xdclass.stress.template;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class TemplateApp {
    public static void main(String[] args) {
        AbstractClass a = new AClass();
        a.templateMethod();

        AbstractClass b = new BClass();
        b.templateMethod();
    }
}
