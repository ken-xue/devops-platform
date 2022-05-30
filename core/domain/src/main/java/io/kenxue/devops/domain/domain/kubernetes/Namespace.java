package io.kenxue.devops.domain.domain.kubernetes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/12/16:09
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Namespace {
    String namespaceName;
    String creationTimestamp;
}
