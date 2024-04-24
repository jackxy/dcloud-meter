package net.xdclass.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小滴课堂-二当家小D,
 * @since 2023-12-22
 */
@Getter
@Setter
@TableName("stress_case")
@Schema(name = "StressCaseDO", description = "")
public class StressCaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "项目id")
    @TableField("project_id")
    private Long projectId;

    @Schema(description = "所属接口模块ID")
    @TableField("module_id")
    private Long moduleId;

    @TableField("environment_id")
    private Long environmentId;

    @Schema(description = "接口名称")
    @TableField("name")
    private String name;

    @Schema(description = "接口描述")
    @TableField("description")
    private String description;

    @Schema(description = "响应断言")
    @TableField("assertion")
    private String assertion;

    @Schema(description = "可变参数")
    @TableField("relation")
    private String relation;

    @Schema(description = "压测类型 [simple jmx]")
    @TableField("stress_source_type")
    private String stressSourceType;

    @Schema(description = "压测参数")
    @TableField("thread_group_config")
    private String threadGroupConfig;

    @Schema(description = "jmx文件地址")
    @TableField("jmx_url")
    private String jmxUrl;

    @Schema(description = "接口路径")
    @TableField("path")
    private String path;

    @Schema(description = "请求方法 [GET POST PUT PATCH DELETE HEAD OPTIONS]")
    @TableField("method")
    private String method;

    @Schema(description = "查询参数")
    @TableField("query")
    private String query;

    @Schema(description = "请求头")
    @TableField("header")
    private String header;

    @Schema(description = "请求体")
    @TableField("body")
    private String body;

    @Schema(description = "请求体格式 [raw form-data json]")
    @TableField("body_type")
    private String bodyType;

    @Schema(description = "创建时间")
    @TableField("gmt_create")
    private Date gmtCreate;

    @Schema(description = "更新时间")
    @TableField("gmt_modified")
    private Date gmtModified;
}
