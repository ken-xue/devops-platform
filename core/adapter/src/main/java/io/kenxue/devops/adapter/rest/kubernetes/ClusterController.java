package io.kenxue.devops.adapter.rest.kubernetes;

import com.alibaba.fastjson.JSONObject;
import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.kubernetes.ClusterAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.*;
import io.kenxue.devops.sharedataboject.kubernetes.enums.AccessWayEnum;
import org.apache.commons.io.IOUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@RestController
@Api(tags = "kubernetes集群模块",description = "包含新增/列表/删除")
@RequestMapping("kubernetes/cluster")
public class ClusterController extends BasicController {
    @Resource
    private ClusterAppService clusterAppService;

    @PostMapping("/add")
    @Permissions("kubernetes:cluster:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid ClusterAddCmd clusterAddCmd) {
        return clusterAppService.add(clusterAddCmd);
    }

    @PostMapping("/import")
    @Permissions("kubernetes:cluster:add")
    @ApiOperation(value = "导入",httpMethod = "POST")
    public Response importing(@RequestParam(value = "file",required = false) MultipartFile file,
                              @RequestParam(value = "info",required = false) String info) throws IOException {
        ClusterDTO clusterDTO = JSONObject.parseObject(info, ClusterDTO.class);
        if (AccessWayEnum.valueOf(clusterDTO.getAccessWay())==AccessWayEnum.CONFIG_FILE){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(file.getInputStream(), baos);
            byte[] bytes = baos.toByteArray();
            clusterDTO.setConfigBytes(bytes);
//            clusterDTO.setConfigStream(file.getInputStream());
        }
        ClusterImportCmd cmd = ClusterImportCmd.builder().info(clusterDTO).build();
        return clusterAppService.importing(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("kubernetes:cluster:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid ClusterDeleteCmd clusterDeleteCmd){
        return clusterAppService.delete(clusterDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("kubernetes:cluster:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid ClusterPageQry clusterPageQry){
        return clusterAppService.page(clusterPageQry);
    }

    @GetMapping("/list")
    @Permissions("kubernetes:cluster:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid ClusterListQry clusterListQry){
        return clusterAppService.list(clusterListQry);
    }

    @GetMapping("/info")
    @Permissions("kubernetes:cluster:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid ClusterGetQry clusterGetQry){
        return clusterAppService.getById(clusterGetQry);
    }

    @PutMapping("/update")
    @Permissions("kubernetes:cluster:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody ClusterUpdateCmd clusterUpdateCmd){
        return clusterAppService.update(clusterUpdateCmd);
    }
}
