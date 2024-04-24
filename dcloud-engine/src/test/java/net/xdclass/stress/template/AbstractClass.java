package net.xdclass.stress.template;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public abstract class AbstractClass {

    /**
     * 模版方法
     */
    public void templateMethod(){

        specialMethod();
        abstractMethod1();
        abstractMethod2();

    }


    public void specialMethod(){
        System.out.println("抽象类的具体方法被调用");
    }

    public abstract void abstractMethod1();

    public abstract void abstractMethod2();
}
