package net.xdclass.req.ui;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
public class UiCaseSaveReq {

    private Long projectId;

    private Long moduleId;

    private String browser;

    private String name;

    private String description;

    private String level;

    private List<UiCaseStepSaveReq> list;

}
