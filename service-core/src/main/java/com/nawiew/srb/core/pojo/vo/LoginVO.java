package com.nawiew.srb.core.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.core.pojo.vo
 * @ClassName:LoginVO
 * @Description:
 * @date 2021/7/8 20:48
 */
@Data
@ApiModel(description = "登入对象")
public class LoginVO {
    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;
}
