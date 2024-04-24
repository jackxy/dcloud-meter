package net.xdclass.dto;

import lombok.Data;

import java.util.List;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Data
public class UiCaseResultDTO {

    private Long reportId;

    private Boolean executeState;

    private Long startTime;

    private Long endTime;

    private Long expendTime;

    private Integer quantity;

    private Integer passQuantity;

    private Integer failQuantity;

    private List<UiCaseResultItemDTO> list;
}
