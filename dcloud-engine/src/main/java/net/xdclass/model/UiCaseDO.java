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
 * @since 2024-03-02
 */
@Getter
@Setter
@TableName("ui_case")
@Schema(name = "UiCaseDO", description = "")
public class UiCaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "所属项目ID")
    @TableField("project_id")
    private Long projectId;

    @Schema(description = "所属UI用例模块ID")
    @TableField("module_id")
    private Long moduleId;

    @Schema(description = "浏览器名称")
    @TableField("browser")
    private String browser;

    @Schema(description = "UI用例名称")
    @TableField("name")
    private String name;

    @Schema(description = "UI用例描述")
    @TableField("description")
    private String description;

    @Schema(description = "执行等级 [p0 p1 p2 p3]")
    @TableField("level")
    private String level;

    @Schema(description = "创建时间")
    @TableField("gmt_create")
    private Date gmtCreate;

    @Schema(description = "更新时间")
    @TableField("gmt_modified")
    private Date gmtModified;
}
