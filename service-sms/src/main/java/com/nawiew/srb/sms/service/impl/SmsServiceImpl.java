package com.nawiew.srb.sms.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.nawiew.common.exception.Assert;
import com.nawiew.common.exception.BusinessException;
import com.nawiew.common.result.ResponseEnum;
import com.nawiew.srb.sms.service.SmsService;
import com.nawiew.srb.sms.util.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.sms.service.impl
 * @ClassName:SmsServiceImpl
 * @Description:
 * @date 2021/7/5 20:38
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public void send(String mobile, String templateCode, Map<String, Object> param) {

        DefaultProfile defaultProfile = DefaultProfile.getProfile(SmsProperties.REGION_Id,SmsProperties.KEY_ID,SmsProperties.KEY_SECRET);
        IAcsClient iAcsClient = new DefaultAcsClient(defaultProfile);
        //创建远程连接的请求参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");// 调用短信接口
        request.putQueryParameter("RegionId", SmsProperties.REGION_Id);
        request.putQueryParameter("PhoneNumbers", mobile);
        String signName = SmsProperties.SIGN_NAME;
        signName = "北京课时教育";
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        Gson gson = new Gson();
        String json = gson.toJson(param);
        request.putQueryParameter("TemplateParam", json);// json格式的参数：{"code":"1234"}
        try {
            //int i = 1/0;
            CommonResponse commonResponse = iAcsClient.getCommonResponse(request);
            boolean success = commonResponse.getHttpResponse().isSuccess();// 判断调用是否成功
            Assert.isTrue(success,ResponseEnum.ALIYUN_RESPONSE_FAIL);

            String data = commonResponse.getData();
            Map map = gson.fromJson(data, param.getClass());
            String code = (String)map.get("Code");

            //ALIYUN_SMS_LIMIT_CONTROL_ERROR(-502, "短信发送过于频繁"),//业务限流
            Assert.notEquals("isv.BUSINESS_LIMIT_CONTROL", code, ResponseEnum.ALIYUN_SMS_LIMIT_CONTROL_ERROR);
            //ALIYUN_SMS_ERROR(-503, "短信发送失败"),//其他失败
            Assert.equals("OK", code, ResponseEnum.ALIYUN_SMS_ERROR);


        } catch (ClientException e) {
            log.error(e.getErrMsg());// 程序失败，将失败原因打印到日志中
            e.printStackTrace();
        }


    }

    @Override
    public void saveCode(String code) {
        redisTemplate.opsForValue().set("srb:sms:code",code);
    }
}
