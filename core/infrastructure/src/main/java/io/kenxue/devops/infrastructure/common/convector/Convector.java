package io.kenxue.devops.infrastructure.common.convector;

import java.util.List;

public interface Convector<Domain,DO> {
    /**
     * 序列化
     * @param domain
     * @return
     */
    DO toDO(Domain domain);

    /**
     * 反序列化
     * @param dO
     * @return
     */
    Domain toDomain(DO dO);

    /**
     * 转DO列表
     * @param domainList
     * @return
     */
    List<DO> toDOList(List<Domain> domainList);

    /**
     * 转Domain列表
     * @param doList
     * @return
     */
    List<Domain> toDomainList(List<DO> doList);
}
