package io.kenxue.devops.adapter.rest.sys;

import io.kenxue.devops.acl.authorize.AuthorizeService;
import io.kenxue.devops.acl.authorize.dto.CaptchaGetDTO;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@RestController
@Tag(name = "验证模块",description = "获取验证码")
@RequestMapping("/captcha")
public class CaptchaController extends BasicController {
    @Resource
    private AuthorizeService authorizeService;
    /**
     * 获取验证码
     */
    @GetMapping("/get")
    @Operation(summary = "获取验证码",method = "GET")
    public void info(@ModelAttribute CaptchaGetDTO captchaGetDTO, HttpServletResponse response) throws Exception{
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        BufferedImage image = authorizeService.generateCaptcha(captchaGetDTO);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }
}
