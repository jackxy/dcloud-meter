package net.xdclass.controller.common;

import jakarta.annotation.Resource;
import net.xdclass.dto.common.SysDictDTO;
import net.xdclass.service.common.SysDictService;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/v1/dict")
public class SysDictController {

    @Resource
    private SysDictService sysDictService;


    /**
     * 根据分类获取字典，支持传递多个，参数格式: xxx,aaa,bbb
     * @param category
     * @return
     */
    @GetMapping("list")
    public JsonData listByCategory(@RequestParam String [] category){

        Map<String, List<SysDictDTO>> stringListMap =  sysDictService.listByCategory(category);
        return JsonData.buildSuccess(stringListMap);
    }

}
