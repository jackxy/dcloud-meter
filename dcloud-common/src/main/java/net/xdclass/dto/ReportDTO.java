package net.xdclass.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小滴课堂-二当家小D,
 * @since 2023-12-22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO{

    private Long id;

    private Long projectId;

    private Long caseId;

    private String type;

    private String name;

    private String executeState;

    private String summary;

    private Long startTime;

    private Long endTime;

    private Long expandTime;

    private Long quantity;

    private Long passQuantity;

    private Long failQuantity;

    private Date gmtCreate;

    private Date gmtModified;
}
