package io.kenxue.devops.adapter.rest.kubernetes;

import io.kenxue.devops.coreclient.api.kubernetes.NamespaceService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceAddCmd;
import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceDeleteCmd;
import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceListQry;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/12/14:28
 * @Description:
 */
@RestController
@Tag(name = "k8s命名空间(namespace)管理",description = "namespace")
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

    @PostMapping("/add")
    public Response add(@RequestBody @Valid NamespaceAddCmd namespaceAddCmd){
        return namespaceService.add(namespaceAddCmd);
    }

    @DeleteMapping("/delete")
    public Response delete(@RequestBody @Valid NamespaceDeleteCmd namespaceDeleteCmd){
        return namespaceService.delete(namespaceDeleteCmd);
    }
}
