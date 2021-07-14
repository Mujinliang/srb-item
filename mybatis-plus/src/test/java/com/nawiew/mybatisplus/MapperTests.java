package com.nawiew.mybatisplus;

import com.nawiew.mybatisplus.entity.User;
import com.nawiew.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nawiew
 * @packageName:com.nawiew.mybatisplus
 * @ClassName:MapperTests
 * @Description:
 * @date 2021/7/1 10:50
 */
@SpringBootTest
public class MapperTests {
    @Resource
    private UserMapper userMapper;


    /**
     * Create 添加操作
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("小李子");
        user.setAge(22);
        user.setEmail("2823@qq.com");
        //影响的行数
        int insert = userMapper.insert(user);
        System.out.println("影响的行数 : " + insert);
        //id自动回填
        System.out.println("id :" + user.getId());
    }

    /**
     * Retrieve 查询操作
     */
    @Test
    public void testSelect() {
        //按id查询
        User user = userMapper.selectById(5L);
        System.out.println("user = " + user);

        //按id列表查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(3L, 4L, 5L));
        users.forEach(System.out::println);

        //按条件查询
        Map<String,Object> map=new HashMap<>();
        map.put("name","小李子");
        map.put("age",22);
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    /**
     * Update 修改操作
     */
    @Test
    public void testUpdate(){
        User user=new User();
        user.setId(4L);
        user.setName("木槿");
        user.setAge(22);

        int result = userMapper.updateById(user);
        System.out.println("影响的行数 : " + result);
    }

    /**
     * Delete
     */
    @Test
    public void testDelete(){
        int result = userMapper.deleteById(3L);
        System.out.println("影响的行数 : " + result);
    }

    @Test
    public void testSelectAllByName(){
        List<User> userList = userMapper.selectAllByName("小李子");
        userList.forEach(System.out::println);
    }
}
