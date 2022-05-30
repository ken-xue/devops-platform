package io.kenxue.devops.coreclient.dto.middleware.minio;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import io.kenxue.devops.sharedataboject.middleware.Node;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Data
@Accessors(chain = true)
public class MinioCreateCmd extends CommonCommand {
    private List<Node> nodeList;
    private String name;
    private String username;
    private String password;
    private String version;
    private String remark;
    /**
     * 安装类型Linux、k8s、docker
     */
    private String type;
    private String responseKey;
}
