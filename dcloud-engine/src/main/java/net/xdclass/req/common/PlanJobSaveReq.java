package net.xdclass.req.common;

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
 * @since 2024-03-27
 */
@Getter
@Setter
public class PlanJobSaveReq {

    private Long projectId;

    private String name;

    private Long caseId;

    private String testType;

    private Integer isDisabled;

    private String executeTime;


}
