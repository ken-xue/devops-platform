package io.kenxue.devops.adapter.rest.kubernetes;

import com.alibaba.fastjson.JSONObject;
import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.kubernetes.ClusterAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.*;
import io.kenxue.devops.sharedataboject.kubernetes.enums.AccessWayEnum;
import io.kenxue.devops.sharedataboject.util.FileUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import java.io.IOException;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@RestController
@Tag(name = "kubernetes集群模块",description = "包含新增/列表/删除")
@RequestMapping("kubernetes/cluster")
public class ClusterController extends BasicController {
    @Resource
    private ClusterAppService clusterAppService;

    @PostMapping("/add")
    @Permissions("kubernetes:cluster:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid ClusterAddCmd clusterAddCmd) {
        return clusterAppService.add(clusterAddCmd);
    }

    @PostMapping("/import")
    @Permissions("kubernetes:cluster:add")
    @Operation(summary = "导入",method = "POST")
    public Response importing(@RequestParam(value = "file",required = false) MultipartFile file,
                              @RequestParam(value = "info",required = false) String info) throws IOException {
        ClusterDTO clusterDTO = JSONObject.parseObject(info, ClusterDTO.class);
        if (AccessWayEnum.valueOf(clusterDTO.getAccessWay())==AccessWayEnum.CONFIG_FILE){
            clusterDTO.setConfigBytes(FileUtil.of(file.getInputStream()));
        }
        ClusterImportCmd cmd = ClusterImportCmd.builder().info(clusterDTO).build();
        return clusterAppService.importing(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("kubernetes:cluster:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid ClusterDeleteCmd clusterDeleteCmd){
        return clusterAppService.delete(clusterDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("kubernetes:cluster:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid ClusterPageQry clusterPageQry){
        return clusterAppService.page(clusterPageQry);
    }

    @GetMapping("/resources")
    @Permissions("kubernetes:cluster:list")
    @Operation(summary = "列表",method = "GET")
    public Response resources(@ModelAttribute @Valid ClusterResourceQry qry){
        return clusterAppService.resources(qry);
    }

    @GetMapping("/pod/describe")
    @Permissions("kubernetes:cluster:list")
    @Operation(summary = "列表",method = "GET")
    public Response describe(@ModelAttribute @Valid ClusterPodDescribeQry qry){
        return clusterAppService.describe(qry);
    }

    @GetMapping("/list")
    @Permissions("kubernetes:cluster:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid ClusterListQry clusterListQry){
        return clusterAppService.list(clusterListQry);
    }

    @GetMapping("/info")
    @Permissions("kubernetes:cluster:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid ClusterGetQry clusterGetQry){
        return clusterAppService.getById(clusterGetQry);
    }

    @PutMapping("/update")
    @Permissions("kubernetes:cluster:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody ClusterUpdateCmd clusterUpdateCmd){
        return clusterAppService.update(clusterUpdateCmd);
    }
}
