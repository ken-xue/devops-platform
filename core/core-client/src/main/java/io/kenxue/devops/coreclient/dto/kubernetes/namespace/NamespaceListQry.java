package io.kenxue.devops.coreclient.dto.kubernetes.namespace;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/12/14:34
 * @Description:
 */
@Data
public class NamespaceListQry extends CommonCommand {
    @NotNull
    private Long id;
}
