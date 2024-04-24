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
 * @since 2024-03-18
 */
@Getter
@Setter
@TableName("social_account")
@Schema(name = "SocialAccountDO", description = "")
public class SocialAccountDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户id")
    @TableField("account_id")
    private Long accountId;

    @Schema(description = "登录类型：phone,mail,weixin,qq")
    @TableField("identity_type")
    private String identityType;

    @Schema(description = "登录账号唯一标识")
    @TableField("identifier")
    private String identifier;

    @Schema(description = "登录凭证，比如密码，token")
    @TableField("credential")
    private String credential;

    @TableField("union_id")
    private String unionId;

    @TableField("gmt_create")
    private Date gmtCreate;

    @TableField("gmt_modified")
    private Date gmtModified;
}
