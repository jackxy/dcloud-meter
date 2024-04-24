package net.xdclass.req.api;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小滴课堂-二当家小D,
 * @since 2024-01-20
 */
@Getter
@Setter
public class ApiCaseModuleUpdateReq {

    private Long id;

    private Long projectId;

    private String name;

}
