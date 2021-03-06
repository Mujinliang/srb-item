package com.nawiew.srb.core.service.impl;

import com.nawiew.srb.core.pojo.entity.UserAccount;
import com.nawiew.srb.core.mapper.UserAccountMapper;
import com.nawiew.srb.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-07-02
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
