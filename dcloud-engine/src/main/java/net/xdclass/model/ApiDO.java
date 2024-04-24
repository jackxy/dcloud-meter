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
 * @since 2024-01-20
 */
@Getter
@Setter
@TableName("api")
@Schema(name = "ApiDO", description = "")
public class ApiDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "项目id")
    @TableField("project_id")
    private Long projectId;

    @Schema(description = "所属API模块ID")
    @TableField("module_id")
    private Long moduleId;

    @Schema(description = "所属环境ID")
    @TableField("environment_id")
    private Long environmentId;

    @Schema(description = "API名称")
    @TableField("name")
    private String name;

    @Schema(description = "描述")
    @TableField("description")
    private String description;

    @Schema(description = "执行等级 [p0 p1 p2 p3]")
    @TableField("level")
    private String level;

    @Schema(description = "请求路径")
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

    @Schema(description = "请求体格式 [form-data x-www-form-urlencoded json raw file]")
    @TableField("body_type")
    private String bodyType;

    @Schema(description = "创建时间")
    @TableField("gmt_create")
    private Date gmtCreate;

    @Schema(description = "更新时间")
    @TableField("gmt_modified")
    private Date gmtModified;
}
