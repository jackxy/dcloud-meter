package net.xdclass.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
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
@TableName("plan_job_log")
@Schema(name = "PlanJobLogDO", description = "")
@Builder
public class PlanJobLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "任务id")
    @TableField("plan_job_id")
    private Long planJobId;

    @Schema(description = "任务名称")
    @TableField("plan_job_name")
    private String planJobName;

    @Schema(description = "执行时间")
    @TableField("execute_time")
    private String executeTime;

    @TableField("gmt_create")
    private Date gmtCreate;

    @TableField("gmt_modified")
    private Date gmtModified;
}
