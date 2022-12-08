package io.kenxue.devops.domain.repository.sys;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigListQry;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigPageQry;
import io.kenxue.devops.domain.domain.sys.Config;
import java.util.List;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
public interface ConfigRepository {
    void create(Config config);
    void update(Config config);
    Config getById(Long id);
    List<Config> list(ConfigListQry configListQry);
    Page<Config> page(ConfigPageQry qry);
}
