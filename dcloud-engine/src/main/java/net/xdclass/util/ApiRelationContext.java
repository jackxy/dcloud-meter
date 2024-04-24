package net.xdclass.util;

import cn.hutool.core.lang.hash.Hash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
public class ApiRelationContext {

    private static final ThreadLocal<Map<String,String>> THREAD_LOCAL = new ThreadLocal<>();

    public static Map<String,String> get(){
        return THREAD_LOCAL.get();
    }


    public static String get(String key){
        Map<String, String> map = THREAD_LOCAL.get();
        if(map == null){
            return null;
        }
        return map.get(key);
    }


    public static void set(String key,String value){
        if(get() == null){
            THREAD_LOCAL.set(new HashMap<>(16));
        }
        THREAD_LOCAL.get().put(key,value);
    }


    /**
     * 除了常规的内存线程变量清空，也需要把相关资源文件进行删除
     */
    public static void remove(){
        String filePaths = get("filePaths");
        if(filePaths!=null){
            String[] split = filePaths.split(",");
            for(String pathStr : split){
                Path filePath = Paths.get(pathStr);
                try {
                    Files.delete(filePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        THREAD_LOCAL.remove();
    }



}
