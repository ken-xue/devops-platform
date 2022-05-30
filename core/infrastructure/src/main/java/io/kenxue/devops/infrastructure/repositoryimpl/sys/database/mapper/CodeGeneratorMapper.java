package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: 麦奇
 * @Date: 21-11-14 下午10:56
 */
@Mapper
public interface CodeGeneratorMapper {

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
