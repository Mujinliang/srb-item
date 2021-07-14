package com.nawiew.srb.core.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nawiew.common.result.R;
import com.nawiew.srb.core.pojo.entity.UserInfo;
import com.nawiew.srb.core.pojo.query.UserInfoQuery;
import com.nawiew.srb.core.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.core.controller.admin
 * @ClassName:AdminUserInfoController
 * @Description:
 * @date 2021/7/9 11:36
 */
@Api(tags = "会员管理")
/*@CrossOrigin*/
@Slf4j
@RestController
@RequestMapping("/admin/core/userInfo")
public class AdminUserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @ApiOperation("获取会员分页列表")
    @GetMapping("/list/{page}/{limit}")
    public R listPage(
            @ApiParam(value = "当前页码",required = true)
            @PathVariable Long page,
            @ApiParam(value = "每页记录数",required = true)
            @PathVariable Long limit,
            @ApiParam(value = "查询对象",required = true)
            UserInfoQuery userInfoQuery){

        Page<UserInfo> pageParam=new Page<>(page,limit);
        IPage<UserInfo> pageModel=userInfoService.listPage(pageParam,userInfoQuery);
        return R.ok().data("pageModel",pageModel);
    }

    @ApiOperation("锁定和解锁")
    @PutMapping("/lock/{id}/{status}")
    public R lock(
            @ApiParam(value = "用户id",required = true)
            @PathVariable("id") Long id,
            @ApiParam(value = "锁定状态(0:锁定 1:解锁)",required = true)
            @PathVariable("status") Integer status){
        userInfoService.lock(id,status);
        return R.ok().message(status==1?"解锁成功":"锁定成功");
    }
}
