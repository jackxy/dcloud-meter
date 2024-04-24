package net.xdclass.dto.api;

import lombok.Getter;
import lombok.Setter;
import net.xdclass.dto.ApiCaseStepDTO;

import java.util.Date;
import java.util.List;

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
public class ApiCaseDTO {



    private Long id;

    private Long projectId;

    private Long moduleId;

    private String name;

    private String description;

    private String level;

    private List<ApiCaseStepDTO> list;

    private Date gmtCreate;

    private Date gmtModified;
}
