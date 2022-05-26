package io.kenxue.cicd.adapter.rest.middleware;

import io.kenxue.cicd.adapter.common.annotation.Permissions;
import io.kenxue.cicd.adapter.rest.common.BasicController;
import io.kenxue.cicd.coreclient.api.middleware.MinioAppService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.middleware.minio.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.validation.Valid;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@RestController
@Api(tags = "minio实例模块",description = "包含新增/列表/删除")
@RequestMapping("middleware/minio")
public class MinioController extends BasicController {
    @Resource
    private MinioAppService minioAppService;

    @PostMapping("/add")
    @Permissions("middleware:minio:add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    public Response add(@RequestBody @Valid MinioAddCmd cmd) {
        return minioAppService.add(cmd);
    }

    @DeleteMapping("/delete")
    @Permissions("middleware:minio:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid MinioDeleteCmd cmd){
        return minioAppService.delete(cmd);
    }

    @GetMapping("/page")
    @Permissions("middleware:minio:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = MinioDTO.class)
    })
    public Response page(@ModelAttribute @Valid MinioPageQry qry){
        return minioAppService.page(qry);
    }

    @GetMapping("/list")
    @Permissions("middleware:minio:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = MinioDTO.class)
    })
    public Response list(@ModelAttribute @Valid MinioListQry qry){
        return minioAppService.list(qry);
    }

    @GetMapping("/info")
    @Permissions("middleware:minio:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 2000, message = "OK", response = MinioDTO.class)
    })
    public Response info(@ModelAttribute @Valid MinioGetQry qry){
        return minioAppService.getById(qry);
    }

    @PutMapping("/update")
    @Permissions("middleware:minio:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody MinioUpdateCmd cmd){
        return minioAppService.update(cmd);
    }
}
