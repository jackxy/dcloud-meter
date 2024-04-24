package net.xdclass.stress.template;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class AClass extends AbstractClass{
    @Override
    public void abstractMethod1() {
        System.out.println("类A的abstractMethod1 被调用");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("类A的abstractMethod2 被调用");
    }
}
