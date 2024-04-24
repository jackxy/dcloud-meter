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
 * @since 2024-03-11
 */
@Getter
@Setter
@TableName("report_detail_ui")
@Schema(name = "ReportDetailUiDO", description = "")
public class ReportDetailUiDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "所属报告ID")
    @TableField("report_id")
    private Long reportId;

    @Schema(description = "执行状态")
    @TableField("execute_state")
    private Boolean executeState;

    @Schema(description = "断言状态")
    @TableField("assertion_state")
    private Boolean assertionState;

    @Schema(description = "异常信息")
    @TableField("exception_msg")
    private String exceptionMsg;

    @Schema(description = "消耗时间")
    @TableField("expand_time")
    private Long expandTime;

    @Schema(description = "所属UI用例ID")
    @TableField("case_id")
    private Long caseId;

    @Schema(description = "序号")
    @TableField("num")
    private Long num;

    @Schema(description = "步骤名称")
    @TableField("name")
    private String name;

    @Schema(description = "步骤操作类型")
    @TableField("operation_type")
    private String operationType;

    @Schema(description = "UI元素定位类型")
    @TableField("location_type")
    private String locationType;

    @Schema(description = "UI元素定位表达式")
    @TableField("location_express")
    private String locationExpress;

    @Schema(description = "元素查找最长等待时间")
    @TableField("element_wait")
    private Long elementWait;

    @Schema(description = "目标UI元素定位类型")
    @TableField("target_location_type")
    private String targetLocationType;

    @Schema(description = "目标UI元素定位表达式")
    @TableField("target_location_express")
    private String targetLocationExpress;

    @Schema(description = "目标元素查找最长等待时间")
    @TableField("target_element_wait")
    private Long targetElementWait;

    @Schema(description = "步骤值")
    @TableField("value")
    private String value;

    @Schema(description = "预期属性")
    @TableField("expect_key")
    private String expectKey;

    @Schema(description = "预期值")
    @TableField("expect_value")
    private String expectValue;

    @Schema(description = "步骤描述")
    @TableField("description")
    private String description;

    @Schema(description = "断言失败是否继续执行")
    @TableField("is_continue")
    private Boolean isContinue;

    @Schema(description = "是否截图")
    @TableField("is_screenshot")
    private Boolean isScreenshot;

    @Schema(description = "截图url")
    @TableField("screenshot_url")
    private String screenshotUrl;

    @TableField("gmt_create")
    private Date gmtCreate;

    @TableField("gmt_modified")
    private Date gmtModified;
}
