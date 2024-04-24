package net.xdclass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.xdclass.model.PermissionDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 小滴课堂-二当家小D,
 * @since 2024-03-18
 */
public interface PermissionMapper extends BaseMapper<PermissionDO> {

    List<String> findPermissionCodeList(@Param("accountId") Long accountId);
}
