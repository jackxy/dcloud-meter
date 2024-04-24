package net.xdclass.req.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Data
public class ApiSaveReq {


    private Long projectId;

    private Long moduleId;

    private Long environmentId;

    private String name;

    private String description;


    private String level;


    private String path;


    private String method;

    private String query;


    private String header;


    private String body;

    private String bodyType;


}
