package io.kenxue.cicd.adapter.rest.kubernetes;

import io.kenxue.cicd.coreclient.api.kubernetes.NamespaceService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.kubernetes.namespace.NamespaceAddCmd;
import io.kenxue.cicd.coreclient.dto.kubernetes.namespace.NamespaceListQry;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/12/14:28
 * @Description:
 */
@RestController
@Api(tags = "k8s命名空间(namespace)管理",description = "namespace")
@RequestMapping("kubernetes/namespace")
public class NamespaceController {

    @Resource
    private NamespaceService namespaceService;

    /**
     * namespace list
     * @param namespaceListQry
     * @return
     */
    @GetMapping("/list")
    public Response list(@ModelAttribute NamespaceListQry namespaceListQry){
        return namespaceService.list(namespaceListQry);
    }

    @PostMapping("add")
    public Response add(@RequestBody @Valid NamespaceAddCmd namespaceAddCmd){
        return namespaceService.add(namespaceAddCmd);
    }
}
