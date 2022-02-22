package io.kenxue.cicd.coreclient.dto.kubernetes.namespace;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/16/15:31
 * @Description:
 */
@Data
public class NamespaceDeleteCmd {
    private String namespace;
}
