package net.xdclass.req.stress;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 小滴课堂-二当家小D,
 * @since 2023-12-22
 */

@Data
public class StressCaseSaveReq implements Serializable {



    private Long projectId;


    private Long moduleId;

    private Long environmentId;

    private String name;

    private String description;

    private String assertion;

    private String relation;

    private String stressSourceType;

    private String threadGroupConfig;

    private String jmxUrl;

    private String path;

    private String method;

    private String query;

    private String header;

    private String body;

    private String bodyType;

}
