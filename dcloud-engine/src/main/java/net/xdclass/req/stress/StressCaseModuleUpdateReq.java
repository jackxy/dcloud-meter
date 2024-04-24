package net.xdclass.req.stress;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小滴课堂-二当家小D,
 * @since 2023-12-22
 */
@Data
public class StressCaseModuleUpdateReq implements Serializable {

    private Long id;

    private Long projectId;

    private String name;

}
