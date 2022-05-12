package io.kenxue.cicd.application.pipeline.pipeline.loader;

import io.kenxue.cicd.application.common.loader.Loader;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoListQry;
import io.kenxue.cicd.domain.domain.machine.MachineInfo;
import io.kenxue.cicd.domain.repository.machine.MachineInfoRepository;
import io.kenxue.cicd.sharedataboject.machine.enums.GroupEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 构建服务器加载器
 * @author biaoyang
 */
@Service
public class BuilderMachineLoader implements Loader<String,MachineInfo> {

    @Resource
    private MachineInfoRepository machineInfoRepository;

    @Override
    public MachineInfo load() {
        return null;
    }

    @Override
    public MachineInfo load(String key) {
        return null;
    }

    @Override
    public List<MachineInfo> loadAll() {
        return machineInfoRepository.list(new MachineInfoListQry().setGroupUuid(GroupEnum.BUILDER_MACHINE.getUuid()));
    }

    @Override
    public List<MachineInfo> loadAll(List<String> keys) {
        return null;
    }
}
