package io.kenxue.cicd.acl.authorize.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 麦奇
 * @Date: 21-11-21 下午4:37
 */
@Accessors(chain = true)
@Data
public class CaptchaValidateDTO {
    @NotBlank
    private String uuid;
    @NotBlank
    private String code;
}