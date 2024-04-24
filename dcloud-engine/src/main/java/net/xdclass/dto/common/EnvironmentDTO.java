package net.xdclass.dto.common;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class EnvironmentDTO {

    private Long id;

    private Long projectId;

    private String name;

    private String protocol;

    private String domain;

    private Integer port;

    private String description;

    private Date gmtCreate;

    private Date gmtModified;
}
