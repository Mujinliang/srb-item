package com.nawiew.srb.sms.service;

import java.util.Map;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.sms.service
 * @ClassName:SmsService
 * @Description:
 * @date 2021/7/5 20:37
 */
public interface SmsService {
    void send(String mobile, String templateCode, Map<String,Object> param);

    void saveCode(String code);
}
