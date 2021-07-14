package com.nawiew.srb.sms.client.fallback;

import com.nawiew.srb.sms.client.CoreUserInfoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.sms.client.fallback
 * @ClassName:CoreUserInfoClientFallback
 * @Description:
 * @date 2021/7/12 8:37
 */
@Service
@Slf4j
public class CoreUserInfoClientFallback implements CoreUserInfoClient {
    @Override
    public boolean checkMobile(String mobile) {
        log.error("远程调用失败，服务熔断");
        return false;
    }
}
