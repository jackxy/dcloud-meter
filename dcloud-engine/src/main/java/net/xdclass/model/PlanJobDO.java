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
 * @since 2024-03-27
 */
@Getter
@Setter
@TableName("plan_job")
@Schema(name = "PlanJobDO", description = "")
public class PlanJobDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "项目id")
    @TableField("project_id")
    private Long projectId;

    @Schema(description = "所属计划ID")
    @TableField("name")
    private String name;

    @Schema(description = "用例ID")
    @TableField("case_id")
    private Long caseId;

    @Schema(description = "用例类型 [ui api stress]")
    @TableField("test_type")
    private String testType;

    @Schema(description = "是否启用")
    @TableField("is_disabled")
    private Integer isDisabled;

    @Schema(description = "定时任务表达式，支持到分钟基本")
    @TableField("execute_time")
    private String executeTime;

    @Schema(description = "创建时间")
    @TableField("gmt_create")
    private Date gmtCreate;

    @Schema(description = "更新时间")
    @TableField("gmt_modified")
    private Date gmtModified;
}
