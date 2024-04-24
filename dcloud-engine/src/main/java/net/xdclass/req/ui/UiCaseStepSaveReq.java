package net.xdclass.req.ui;

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
 * @since 2024-03-02
 */
@Getter
@Setter
public class UiCaseStepSaveReq implements Serializable {


    private Long projectId;

    private Long caseId;

    private Long num;

    private String name;

    private String operationType;

    private String locationType;

    private String locationExpress;

    private Long elementWait;

    private String targetLocationType;

    private String targetLocationExpress;

    private Long targetElementWait;

    private String value;

    private String expectKey;

    private String expectValue;

    private String description;

    private Boolean isContinue;

    private Boolean isScreenshot;


}
