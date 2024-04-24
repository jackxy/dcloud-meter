package net.xdclass.util;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.util.JMeterUtils;

import java.io.File;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class StressTestUtil {

    /**
     * 获取Jmeter的home路径，临时写法，后续部署上线会调整
     * @return
     */
    public static String getJmeterHome(){
        String osName = System.getProperty("os.name");
        try {
            if(osName.contains("Mac")){
                return "/Users/xdclass/Desktop/jmeter";
            }else {
                //生产环境
                return "/apache-jmeter-5.5";
            }
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }


    /**
     * 获取jmeter bin目录
     * @return
     */
    public static String getJmeterHomeBin(){
        return getJmeterHome() + File.separator + "bin";
    }


    /**
     * 初始化jmeter的配置文件
     */
    public static void initJmeterProperties(){
        String jmeterHome = getJmeterHome();
        String jmeterHomeBin = getJmeterHomeBin();

        //加载jmeter的配置文件
        JMeterUtils.loadJMeterProperties(jmeterHomeBin + File.separator + "jmeter.properties");

        //设置jmeter的安装目录
        JMeterUtils.setJMeterHome(jmeterHome);

        //避免中文响应乱码
        JMeterUtils.setProperty("sampleresult.default.encoding","UTF-8");

        //初始化本地环境
        JMeterUtils.initLocale();
    }


    public static StandardJMeterEngine getJmeterEngine(){
        //初始化配置
        initJmeterProperties();
        StandardJMeterEngine jmeterEngine = new StandardJMeterEngine();
        return jmeterEngine;
    }

}
