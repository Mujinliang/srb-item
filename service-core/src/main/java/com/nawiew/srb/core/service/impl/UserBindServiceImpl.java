package com.nawiew.srb.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.nawiew.common.exception.Assert;
import com.nawiew.common.result.ResponseEnum;
import com.nawiew.srb.core.enums.UserBindEnum;
import com.nawiew.srb.core.hfb.FormHelper;
import com.nawiew.srb.core.hfb.HfbConst;
import com.nawiew.srb.core.hfb.RequestHelper;
import com.nawiew.srb.core.mapper.UserInfoMapper;
import com.nawiew.srb.core.pojo.entity.UserBind;
import com.nawiew.srb.core.mapper.UserBindMapper;
import com.nawiew.srb.core.pojo.vo.UserBindVO;
import com.nawiew.srb.core.service.UserBindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.velocity.runtime.directive.contrib.For;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户绑定表 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-07-02
 */
@Service
public class UserBindServiceImpl extends ServiceImpl<UserBindMapper, UserBind> implements UserBindService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public String commitBindUser(UserBindVO userBindVO, Long userId) {
        //查询身份证号码是否绑定
        String idCard = userBindVO.getIdCard();
        QueryWrapper<UserBind> userBindQueryWrapper=new QueryWrapper<>();
        userBindQueryWrapper.eq("id_Card",idCard).ne("user_id",userId);
        UserBind bind = baseMapper.selectOne(userBindQueryWrapper);
        Assert.isNull(bind, ResponseEnum.USER_BIND_IDCARD_EXIST_ERROR);
        //查询用户绑定信息
        userBindQueryWrapper=new QueryWrapper<>();
        userBindQueryWrapper.eq("user_id",userId);
        UserBind userBind=baseMapper.selectOne(userBindQueryWrapper);
        //判定是否有绑定记录
        if(null==userBind){
            //如果未创建绑定记录，则创建一条记录
            userBind=new UserBind();
            BeanUtils.copyProperties(userBindVO,userBind);
            userBind.setUserId(userId);
            userBind.setStatus(UserBindEnum.NO_BIND.getStatus());
            baseMapper.insert(userBind);
        }else {
            //曾经跳转到托管平台，但是未操作完成，此时将用户最新填写的数据同步到userBind对象
            BeanUtils.copyProperties(userBindVO,userBind);
            baseMapper.updateById(userBind);
        }

        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("agentId", HfbConst.AGENT_ID);
        paramMap.put("agentUserId", userId);
        paramMap.put("idCard",userBindVO.getIdCard());
        paramMap.put("personalName", userBindVO.getName());
        paramMap.put("bankType", userBindVO.getBankType());
        paramMap.put("bankNo", userBindVO.getBankNo());
        paramMap.put("mobile", userBindVO.getMobile());
        paramMap.put("returnUrl", HfbConst.USERBIND_RETURN_URL);
        paramMap.put("notifyUrl", HfbConst.USERBIND_NOTIFY_URL);
        paramMap.put("timestamp", RequestHelper.getTimestamp());

        paramMap.put("sign", RequestHelper.getSign(paramMap));



        String formStr = FormHelper.buildForm(HfbConst.USERBIND_URL,paramMap);
        return formStr;


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void notify(Map<String, Object> paramMap) {

        String bindCode = (String)paramMap.get("bindCode");
        //会员id
        String agentUserId = (String)paramMap.get("agentUserId");
        //根据user_id查询user_bind记录
        QueryWrapper<UserBind> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",agentUserId);
        //更新用户绑定表
        UserBind userBind = baseMapper.selectOne(queryWrapper);
        //更新用户表
    }
}
