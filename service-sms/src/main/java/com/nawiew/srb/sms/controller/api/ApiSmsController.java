package com.nawiew.srb.sms.controller.api;

import com.nawiew.common.exception.Assert;
import com.nawiew.common.result.R;
import com.nawiew.common.result.ResponseEnum;
import com.nawiew.common.util.RandomUtils;
import com.nawiew.common.util.RegexValidateUtils;
import com.nawiew.srb.sms.client.CoreUserInfoClient;
import com.nawiew.srb.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.sms.controller.api
 * @ClassName:ApiSmsController
 * @Description:
 * @date 2021/7/5 21:08
 */
@RestController
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
//@CrossOrigin //跨域
@Slf4j
public class ApiSmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CoreUserInfoClient coreUserInfoClient;

    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public R send(
            @ApiParam(value = "手机号", required = true)
            @PathVariable String mobile){

        //MOBILE_NULL_ERROR(-202, "手机号不能为空"),
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        //MOBILE_ERROR(-203, "手机号不正确"),
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);
        //手机号是否注册
        boolean result = coreUserInfoClient.checkMobile(mobile);
        Assert.isTrue(!result,ResponseEnum.MOBILE_EXIST_ERROR);
        //生成验证码
        String code = RandomUtils.getFourBitRandom();
        //组装短信模板参数
        Map<String,Object> param = new HashMap<>();
        param.put("code", code);
        //发送短信
        //smsService.send(mobile, SmsProperties.TEMPLATE_CODE, param);

        //将验证码存入redis
        redisTemplate.opsForValue().set("srb:sms:code"+mobile, 1223, 5, TimeUnit.MINUTES);

        return R.ok().message("短信发送成功");
    }
}
