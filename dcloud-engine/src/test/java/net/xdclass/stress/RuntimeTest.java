package net.xdclass.stress;

import org.apache.coyote.http11.filters.BufferedInputFilter;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/

public class RuntimeTest {

    public static void main(String[] args) {
//        try {
//            Process process = Runtime.getRuntime().exec("/Users/xdclass/Desktop/coding/apache-jmeter-5.5/bin/jmeter -n -t /Users/xdclass/Desktop/test.jmx -l results.log -e -o /Users/xdclass/Desktop/result");
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//            String line ;
//            while ((line = reader.readLine())!=null){
//                System.out.println(line);
//            }
//            //等待执行结束
//            int waitedForCode  = process.waitFor();
//            System.out.println("压测执行结果状态码："+waitedForCode);
//
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        testNewSwitch2(2);


    }


    public static void testNewSwitch1(int i){
        switch(i){
            case 0 -> {
                System.out.println("zero");
                System.out.println("这是多⾏语句测试案例");
            }
            case 1,111 -> System.out.println("one");
            case 2,22,9999 -> System.out.println("two");
            default -> System.out.println("default");
        }
    }


    public static  void testNewSwitch2(int week ) {

        String day = switch (week){
            case 1 -> {
                System.out.println("星期日，小滴课堂老王和湧哥今天去会所按摩");
                yield "星期日";
            }
            case 2,3,4,5,6 -> {
                System.out.println("工作日，冰冰和Anna白天再摸鱼");
                yield "2-6工作日";
            }
            case 7 -> {
                System.out.println("星期六，小滴课堂湧哥独自录制算法大课熬夜中");
                yield "星期六";
            }
            default -> {
                System.out.println("假期放假被安排相亲");
                yield "其他日期";
            }
        };
        System.out.println("day = " + day);
    }


    public static void testOldSwitch1(int i){

        switch(i){
            case 0:
                System.out.println("zero");
            case 1:
                System.out.println("one");
            case 2:
                System.out.println("two");
            default:
                System.out.println("default");
        }
    }

    public static void testOldSwitch2(int i){
        switch(i){
            case 0:
                System.out.println("zero");
                break;
            case 1:
                System.out.println("one");
                break;
            case 2:
                System.out.println("two");
                break;
            default:
                System.out.println("default");
        }
    }


}
