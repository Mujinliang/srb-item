package com.nawiew.mybatisplus;

import com.nawiew.mybatisplus.entity.User;
import com.nawiew.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {
    @Resource
    private UserMapper userMapper;
    @Test
    void userList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
