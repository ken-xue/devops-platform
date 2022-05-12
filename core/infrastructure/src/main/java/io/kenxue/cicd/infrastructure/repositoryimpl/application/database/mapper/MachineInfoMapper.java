package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoPageQry;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.MachineInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Mapper
public interface MachineInfoMapper extends BaseMapper<MachineInfoDO> {

    /**
     * @param iPage
     * @param qry
     * @return
     */
    List<MachineInfoDO> queryPage(IPage iPage, @Param("qry") MachineInfoPageQry qry);
}
