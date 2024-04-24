package net.xdclass.req.api;

import lombok.Data;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Data
public class ApiUpdateReq {

    private Long id;

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
