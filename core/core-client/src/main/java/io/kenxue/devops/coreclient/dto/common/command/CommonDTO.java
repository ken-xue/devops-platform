package io.kenxue.devops.coreclient.dto.common.command;

import lombok.Data;

import java.util.Date;

@Data
public class CommonDTO extends DTO {
    private Long id;
    private String uuid;
    private Date gmtCreate;
    private Date gmtModified;
    private String creator;
    private String modifier;
    private Boolean deleted;
}
