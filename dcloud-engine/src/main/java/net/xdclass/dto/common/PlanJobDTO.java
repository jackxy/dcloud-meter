package net.xdclass.dto.common;

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
 * @since 2024-03-27
 */
@Getter
@Setter
public class PlanJobDTO implements Serializable {


    private Long id;

    private Long projectId;

    private String name;

    private Long caseId;

    private String testType;

    private Integer isDisabled;

    private String executeTime;

    private Date gmtCreate;

    private Date gmtModified;
}
