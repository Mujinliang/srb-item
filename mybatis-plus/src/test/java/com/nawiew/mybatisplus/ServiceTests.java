package com.nawiew.mybatisplus;

import com.nawiew.mybatisplus.entity.User;
import com.nawiew.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nawiew
 * @packageName:com.nawiew.mybatisplus
 * @ClassName:ServiceTests
 * @Description:
 * @date 2021/7/1 11:07
 */
@SpringBootTest
public class ServiceTests {
    @Resource
    private UserService userService;

    /**
     *  查询总记录数
     */
     @Test
    public void testCount(){
        int count = userService.count();
        System.out.println("总记录数 : " + count);
    }
    @Test
    public void testSaveBatch(){
        ArrayList<User> list=new ArrayList<>();
        for (int i=0;i<5l;i++){
            User user=new User();
            user.setName("Helen"+i);
            user.setAge(20+i);
            list.add(user);
        }
        userService.saveBatch(list);
    }

    @Test
    public void testListAllByName(){
        List<User> users = userService.listAllByName("木子");
        users.forEach(System.out::println);
    }
}
