package com.nawiew.srb.sms;

import com.nawiew.srb.sms.util.SmsProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.sms
 * @ClassName:UtilsTests
 * @Description:
 * @date 2021/7/5 20:34
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilsTests {
    @Test
    public void testProperties(){
        System.out.println(SmsProperties.KEY_ID);
        System.out.println(SmsProperties.KEY_SECRET);
        System.out.println(SmsProperties.REGION_Id);
    }
}
