package net.xdclass.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportSaveReq {

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 用例id
     */
    private Long caseId;

    /**
     * 类型 UI ,API ,STRESS
     */
    private String type;

    private String name;

    /**
     * 执行状态
     */
    private String executeState;

    /**
     * 开始时间
     */
    private Long startTime;


}
