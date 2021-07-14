package com.nawiew.mybatisplus;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nawiew.mybatisplus.entity.User;
import com.nawiew.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nawiew
 * @packageName:com.nawiew.mybatisplus
 * @ClassName:InterceptorTests
 * @Description:
 * @date 2021/7/1 18:19
 */
@SpringBootTest
public class InterceptorTests {
    @Resource
    private UserMapper userMapper;
    @Test
    public void testSelectPage(){
        Page<User> page=new Page<>(1,3);
        Page<User> userPage = userMapper.selectPage(page, null);
        System.out.println(userPage);
        long total = userPage.getTotal();
        System.out.println("总记录数 = " + total);
        List<User> userList = userPage.getRecords();
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectPageVo(){
        Page<User> page=new Page<>(1,4);
        IPage<User> userIPage = userMapper.selectPageByPage(page, 20);
        List<User> userList = userIPage.getRecords();
        userList.forEach(System.out::println);
    }

}
