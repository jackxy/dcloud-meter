package net.xdclass.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.xdclass.util.ExcelUtil;

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
public class ReportExcelDTO {

    @ExcelProperty("id")
    @ColumnWidth(10)
    private Long id;

    /**
     * 所属项目ID
     */
    @ExcelProperty("所属项目ID")
    @ColumnWidth(10)
    private Long projectId;

    /**
     * 用例id
     */
    @ExcelProperty("用例id")
    @ColumnWidth(10)
    private Long caseId;

    /**
     * 报告类型 枚举ReportTypeEnum
     */
    @ExcelProperty("报告类型")
    @ColumnWidth(10)
    private String type;

    /**
     * 报告名称
     */
    @ExcelProperty("报告名称")
    @ColumnWidth(20)
    private String name;

    /**
     * 执行状态 枚举 ReportStateEnum
     */
    @ExcelProperty("执行状态")
    @ColumnWidth(20)
    private String executeState;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间",converter = ExcelUtil.LongTimeConverter.class)
    @ColumnWidth(20)

    private Long startTime;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间",converter = ExcelUtil.LongTimeConverter.class)
    @ColumnWidth(20)
    private Long endTime;

    /**
     * 消耗时间
     */
    @ExcelProperty("消耗时间")
    @ColumnWidth(10)
    private Long expandTime;

    /**
     * 步骤数量
     */
    @ExcelProperty("步骤数量")
    @ColumnWidth(10)
    private Long quantity;

    /**
     * 通过数量
     */
    @ExcelProperty("通过数量")
    @ColumnWidth(10)
    private Long passQuantity;

    /**
     * 失败数量
     */
    @ExcelProperty("失败数量")
    @ColumnWidth(10)
    private Long failQuantity;

    /**
     * 测试报告概述
     */
    @ExcelProperty("报告概述")
    @ColumnWidth(40)
    private String summary;

    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ColumnWidth(40)
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @ExcelProperty("修改时间")
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ColumnWidth(40)
    private Date gmtModified;



}
