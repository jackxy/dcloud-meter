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
 * @since 2024-01-31
 */
@Getter
@Setter
@TableName("report_detail_api")
@Schema(name = "ReportDetailApiDO", description = "")
public class ReportDetailApiDO implements Serializable {

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
    @TableField("expend_time")
    private Long expendTime;

    @Schema(description = "请求头")
    @TableField("request_header")
    private String requestHeader;

    @Schema(description = "请求参数")
    @TableField("request_query")
    private String requestQuery;

    @Schema(description = "请求体")
    @TableField("request_body")
    private String requestBody;

    @Schema(description = "响应头数据")
    @TableField("response_header")
    private String responseHeader;

    @Schema(description = "响应体数据")
    @TableField("response_body")
    private String responseBody;

    @Schema(description = "所属环境ID")
    @TableField("environment_id")
    private Long environmentId;

    @Schema(description = "所属API用例ID")
    @TableField("case_id")
    private Long caseId;

    @Schema(description = "序号")
    @TableField("num")
    private Long num;

    @Schema(description = "API用例步骤名称")
    @TableField("name")
    private String name;

    @Schema(description = "API用例步骤描述")
    @TableField("description")
    private String description;

    @Schema(description = "响应断言")
    @TableField("assertion")
    private String assertion;

    @Schema(description = "关联参数")
    @TableField("relation")
    private String relation;

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

    @TableField("gmt_create")
    private Date gmtCreate;

    @TableField("gmt_modified")
    private Date gmtModified;
}
