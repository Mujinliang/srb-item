package com.nawiew.srb.core;

import com.nawiew.srb.core.mapper.DictMapper;
import com.nawiew.srb.core.pojo.entity.Dict;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.core
 * @ClassName:RedisTemplateTests
 * @Description:
 * @date 2021/7/6 11:37
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTests {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private DictMapper dictMapper;
    @Test
    public void saveDict(){
        Dict dict = dictMapper.selectById(1);
        System.out.println("dict = " + dict);
        //向数据库中存储string类型的键值对, 过期时间5分钟
        redisTemplate.opsForValue().set("dict",dict,5, TimeUnit.MINUTES);
    }
    @Test
    public void getDict(){
        Dict dict = (Dict)redisTemplate.opsForValue().get("dict");
        System.out.println(dict);
    }
}
