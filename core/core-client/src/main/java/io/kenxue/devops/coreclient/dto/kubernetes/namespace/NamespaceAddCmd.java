package io.kenxue.devops.coreclient.dto.kubernetes.namespace;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/12/21:11
 * @Description:
 */
@Data
public class NamespaceAddCmd {
    private String namespaceName;
}
