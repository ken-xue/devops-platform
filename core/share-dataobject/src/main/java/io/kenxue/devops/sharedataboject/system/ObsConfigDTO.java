package io.kenxue.devops.sharedataboject.system;

import lombok.Data;

@Data
public class ObsConfigDTO {
    private String type;
    private String url;
    private String username;
    private String password;
}
