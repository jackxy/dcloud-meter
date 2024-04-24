package net.xdclass.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

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
public class ReportDetailApiDTO implements Serializable {


    private Long id;

    private Long reportId;

    private Boolean executeState;

    private Boolean assertionState;

    private String exceptionMsg;

    private Long expendTime;

    private String requestHeader;

    private String requestQuery;

    private String requestBody;

    private String responseHeader;

    private String responseBody;

    private Long environmentId;

    private Long caseId;

    private Long num;

    private String name;

    private String description;

    private String assertion;

    private String relation;

    private String path;


    private String method;

    private String query;

    private String header;

    private String body;

    private String bodyType;

    private Date gmtCreate;

    private Date gmtModified;
}
