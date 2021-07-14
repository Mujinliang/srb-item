package com.nawiew.srb.core.controller.api;


import com.nawiew.common.exception.Assert;
import com.nawiew.common.result.R;
import com.nawiew.common.result.ResponseEnum;
import com.nawiew.srb.base.util.JwtUtils;
import com.nawiew.srb.core.mapper.UserInfoMapper;
import com.nawiew.srb.core.pojo.vo.LoginVO;
import com.nawiew.srb.core.pojo.vo.RegisterVO;
import com.nawiew.srb.core.pojo.vo.UserInfoVO;
import com.nawiew.srb.core.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.K;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户基本信息 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2021-07-02
 */
@Api(tags = "会员接口")
@Slf4j
@RestController
@RequestMapping("/api/core/userInfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVO registerVO){
        //手机号码
        String mobile = registerVO.getMobile();
        //验证码
        String code = registerVO.getCode();
        //密码
        String password = registerVO.getPassword();
        //手机号码不能为空
        Assert.notNull(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        //验证码不能为空
        Assert.notNull(code, ResponseEnum.CODE_NULL_ERROR);
        //密码不能为空
        Assert.notNull(password, ResponseEnum.PASSWORD_NULL_ERROR);
        //从redis中获取验证码
        String codeGen = redisTemplate.opsForValue().get("srb:sms:code" + mobile).toString();
        //检验验证码
        Assert.equals(code,codeGen,ResponseEnum.CODE_ERROR);
        //注册
        userInfoService.register(registerVO);
        return R.ok().message("注册成功");
    }


    @ApiOperation("会员登入")
    @PostMapping("/login")
    public R login(@RequestBody LoginVO loginVO, HttpServletRequest request){
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        Assert.notEmpty(mobile,ResponseEnum.MOBILE_NULL_ERROR);
        Assert.notEmpty(password,ResponseEnum.PASSWORD_NULL_ERROR);

        String ip = request.getRemoteAddr();
        UserInfoVO userInfoVO=userInfoService.login(loginVO,ip);
        return R.ok().data("userInfo",userInfoVO);
    }
    @ApiOperation("校验令牌")
    @GetMapping("/checkToken")
    public R checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        boolean result = JwtUtils.checkToken(token);
        if(result){
            return R.ok();
        }else {
            return R.setResult(ResponseEnum.LOGIN_AUTH_ERROR);
        }
    }

    @ApiOperation("校验手机号是否注册")
    @GetMapping("/checkMobile/{mobile}")
    public boolean checkMobile(@ApiParam(value = "手机号",required = true) @PathVariable String mobile){
        return userInfoService.checkMobile(mobile);
    }
}

