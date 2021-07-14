package com.nawiew.srb.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nawiew.common.exception.Assert;
import com.nawiew.common.result.ResponseEnum;
import com.nawiew.common.util.MD5;
import com.nawiew.srb.base.util.JwtUtils;
import com.nawiew.srb.core.mapper.UserAccountMapper;
import com.nawiew.srb.core.mapper.UserLoginRecordMapper;
import com.nawiew.srb.core.pojo.entity.UserAccount;
import com.nawiew.srb.core.pojo.entity.UserInfo;
import com.nawiew.srb.core.mapper.UserInfoMapper;
import com.nawiew.srb.core.pojo.entity.UserLoginRecord;
import com.nawiew.srb.core.pojo.query.UserInfoQuery;
import com.nawiew.srb.core.pojo.vo.LoginVO;
import com.nawiew.srb.core.pojo.vo.RegisterVO;
import com.nawiew.srb.core.pojo.vo.UserInfoVO;
import com.nawiew.srb.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-07-02
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private UserLoginRecordMapper userLoginRecordMapper;

    @Override
    public void register(RegisterVO registerVO) {
        //判断用户是否被注册
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("mobile",registerVO.getMobile());
        Integer count = baseMapper.selectCount(queryWrapper);
        Assert.isTrue(count==0, ResponseEnum.MOBILE_EXIST_ERROR);


        //插入用户基本信息
        UserInfo userInfo=new UserInfo();
        userInfo.setUserType(registerVO.getUserType());
        userInfo.setMobile(registerVO.getMobile());
        userInfo.setPassword(MD5.encrypt(registerVO.getPassword()));
        userInfo.setName(registerVO.getMobile());
        userInfo.setNickName(registerVO.getMobile());
        userInfo.setStatus(UserInfo.STATUS_NORMAL);
        //设置一张静态资源服务器上的头像图片
        userInfo.setHeadImg("https://nawiew.oss-cn-shanghai.aliyuncs.com/img/2021/07/07/2a9ff3ec-3bce-49b1-9ec3-89a8f9b2db1f.jpg");
        baseMapper.insert(userInfo);

        //创建会员账户
        UserAccount userAccount=new UserAccount();
        userAccount.setUserId(userInfo.getId());
        userAccountMapper.insert(userAccount);

    }


    @Transactional( rollbackFor = {Exception.class})
    @Override
    public UserInfoVO login(LoginVO loginVO, String ip) {
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        Integer userType = loginVO.getUserType();

        //获取会员
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        queryWrapper.eq("user_type",userType);

        UserInfo userInfo = baseMapper.selectOne(queryWrapper);
        //用户不存在
        Assert.notNull(userInfo,ResponseEnum.LOGIN_MOBILE_ERROR);
        //校验密码
        Assert.equals(MD5.encrypt(password),userInfo.getPassword(),ResponseEnum.LOGIN_PASSWORD_ERROR);
        //用户是否被禁用
        Assert.equals(userInfo.getStatus(),UserInfo.STATUS_NORMAL,ResponseEnum.LOGIN_LOKED_ERROR);

        //记录登录日志
        UserLoginRecord userLoginRecord = new UserLoginRecord();
        userLoginRecord.setUserId(userInfo.getId());
        userLoginRecord.setIp(ip);
        userLoginRecordMapper.insert(userLoginRecord);

        //生成token
        String token = JwtUtils.createToken(userInfo.getId(), userInfo.getName());
        UserInfoVO userInfoVO=new UserInfoVO();
        userInfoVO.setToken(token);
        userInfoVO.setName(userInfo.getName());
        userInfoVO.setNickName(userInfo.getNickName());
        userInfoVO.setHeadImg(userInfo.getHeadImg());
        userInfoVO.setMobile(userInfo.getMobile());
        userInfoVO.setUserType(userInfo.getUserType());

        return userInfoVO;
    }

    @Override
    public IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery) {
        String mobile = userInfoQuery.getMobile();
        Integer status = userInfoQuery.getStatus();
        Integer userType = userInfoQuery.getUserType();

        QueryWrapper<UserInfo> userInfoQueryWrapper=new QueryWrapper<>();

        if (userInfoQuery==null){
            return baseMapper.selectPage(pageParam,null);
        }

        userInfoQueryWrapper
                .eq(StringUtils.isNotBlank(mobile),"mobile", mobile)
                .eq(status!=null,"status",userInfoQuery.getStatus())
                .eq(userType!=null,"user_type",userType);
        return baseMapper.selectPage(pageParam,userInfoQueryWrapper);
    }


    @Override
    public void lock(Long id, Integer status) {
        UserInfo userInfo=new UserInfo();
        userInfo.setId(id);
        userInfo.setStatus(status);
        baseMapper.updateById(userInfo);
    }

    @Override
    public boolean checkMobile(String mobile) {
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count>0;
    }

}
