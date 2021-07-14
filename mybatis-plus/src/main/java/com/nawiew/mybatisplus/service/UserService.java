package com.nawiew.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nawiew.mybatisplus.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> listAllByName(String name);
}
