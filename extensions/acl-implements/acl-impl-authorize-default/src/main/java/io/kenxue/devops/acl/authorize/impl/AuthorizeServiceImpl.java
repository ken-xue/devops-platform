package io.kenxue.devops.acl.authorize.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import io.kenxue.devops.acl.authorize.AuthorizeService;
import io.kenxue.devops.acl.authorize.constant.Constant;
import io.kenxue.devops.acl.authorize.dto.CaptchaGetDTO;
import io.kenxue.devops.acl.authorize.dto.CaptchaValidateDTO;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.CaptchaDO;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.mapper.CaptchaMapper;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: 麦奇
 * @Date: 21-11-13 下午2:04
 */
@Component
@Slf4j
public class AuthorizeServiceImpl implements AuthorizeService {

    @Resource
    private CaptchaMapper captchaMapper;
    @Resource
    private Producer producer;

    
    public List<String> getPermissions() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream().map(v-> v.getAuthority().trim()).collect(Collectors.toList());
    }

    
    public Boolean captchaValidate(CaptchaValidateDTO captchaValidateDTO) {
        CaptchaDO one = captchaMapper.selectOne(new QueryWrapper<CaptchaDO>().eq("uuid", captchaValidateDTO.getUuid()));
        if(Objects.isNull(one))return false;
        captchaMapper.delete(new QueryWrapper<CaptchaDO>().eq("uuid", captchaValidateDTO.getUuid()));
        if(one.getCode().equalsIgnoreCase(captchaValidateDTO.getCode()) && one.getExpireTime().getTime() >= System.currentTimeMillis())return true;
        return false;
    }

    
    public BufferedImage generateCaptcha(CaptchaGetDTO captchaGetQry) {
        //生成文字验证码
        String code = producer.createText();
        CaptchaDO captchaDO = new CaptchaDO();
        captchaDO.setUuid(captchaGetQry.getUuid());
        captchaDO.setCode(code);
        //5分钟后过期
        captchaDO.setExpireTime(new DateTime(new Date()).plusMinutes(Constant.captchaExpireTime).toDate());
        //删除过期的验证码
        captchaMapper.delete(new QueryWrapper<CaptchaDO>().lt("expire_time",new Date()));
        //保存验证码
        captchaMapper.insert(captchaDO);
        return producer.createImage(code);
    }
}
