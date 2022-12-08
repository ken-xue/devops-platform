package io.kenxue.devops.adapter.rest.middleware;

import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.middleware.MinioAppService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.middleware.minio.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@RestController
@Tag(name = "minio实例模块",description = "包含新增/列表/删除")
@RequestMapping("middleware/minio")
public class MinioController extends BasicController {
    @Resource
    private MinioAppService minioAppService;

    @PostMapping("/add")
    @Permissions("middleware:minio:add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid MinioAddCmd cmd) {
        return minioAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("middleware:minio:delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid MinioDeleteCmd cmd){
        return minioAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("middleware:minio:page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid MinioPageQry qry){
        return minioAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("middleware:minio:list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid MinioListQry qry){
        return minioAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("middleware:minio:info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid MinioGetQry qry){
        return minioAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("middleware:minio:update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody MinioUpdateCmd cmd){
        return minioAppService.update(cmd);
    }
}
