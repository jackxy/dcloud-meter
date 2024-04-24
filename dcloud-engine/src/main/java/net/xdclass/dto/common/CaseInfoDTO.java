package net.xdclass.dto.common;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class CaseInfoDTO {

    /**
     * 用例id，或者步骤id
     */
    private Long id;

    /**
     * 模块id
     */
    private Long moduleId;

    /**
     * 名称
     */
    private String name;
}
