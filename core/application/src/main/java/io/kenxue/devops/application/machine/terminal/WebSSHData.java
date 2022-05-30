package io.kenxue.devops.application.machine.terminal;

import lombok.Data;

/**
* @Description: webssh数据传输
*/
@Data
public class WebSSHData {
    //操作
    private String operate;
    private String host;
    //端口号默认为22
    private Integer port = 22;
    private String username;
    private String password;
    private String command = "";
}
