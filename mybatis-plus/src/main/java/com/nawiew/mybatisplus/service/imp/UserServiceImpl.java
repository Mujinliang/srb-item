package com.nawiew.mybatisplus.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nawiew.mybatisplus.entity.User;
import com.nawiew.mybatisplus.mapper.UserMapper;
import com.nawiew.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nawiew
 * @packageName:com.nawiew.mybatisplus.service.imp
 * @ClassName:UserServiceImpl
 * @Description:
 * @date 2021/7/1 11:06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public List<User> listAllByName(String name) {
        // baseMapper对象指向当前业务的mapper对象
        return baseMapper.selectAllByName(name);
    }
}
