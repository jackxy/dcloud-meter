package net.xdclass.dto.stress;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
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
public class StressCaseModuleDTO {

    private Long id;

    private Long projectId;

    private String name;

    private Date gmtCreate;

    private Date gmtModified;

    private List<StressCaseDTO> list;
}
