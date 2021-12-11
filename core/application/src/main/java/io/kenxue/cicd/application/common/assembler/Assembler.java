package io.kenxue.cicd.application.common.assembler;

import io.kenxue.cicd.coreclient.dto.common.page.Page;

import java.util.List;

/**
 * @Author: 麦奇
 * @Date: 21-11-26 下午9:48
 */
public interface Assembler<DTO, Domain> {
    DTO toDTO(Domain dO);
    Domain toDomain(DTO dTO);
    List<DTO> toDTOList(List<Domain> domainList);
    List<Domain> toDomainList(List<DTO> dTOList);
    Page<DTO> toDTOPage(Page<Domain> page);
}
