package net.xdclass.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

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
@TableName("sys_dict")
@Schema(name = "SysDictDO", description = "")
public class SysDictDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "自增主键")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "分类")
    @TableField("category")
    private String category;

    @Schema(description = "分类中文名称")
    @TableField("category_name")
    private String categoryName;

    @Schema(description = "字典项名称")
    @TableField("name")
    private String name;

    @Schema(description = "字典项value")
    @TableField("value")
    private String value;

    @Schema(description = "字典项拓展字段")
    @TableField("extend")
    private String extend;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "创建时间")
    @TableField("gmt_create")
    private Date gmtCreate;

    @Schema(description = "更新时间")
    @TableField("gmt_modified")
    private Date gmtModified;
}
