package com.nawiew.srb.core.controller.admin;

import com.nawiew.common.result.R;
import com.nawiew.srb.core.pojo.entity.UserLoginRecord;
import com.nawiew.srb.core.service.UserLoginRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.core.controller.admin
 * @ClassName:AdminUserLoginRecordController
 * @Description:
 * @date 2021/7/9 19:26
 */
/*@CrossOrigin*/
@Slf4j
@Api(tags = "会员登录日志接口")
@RestController
@RequestMapping("/admin/core/userLoginRecord")
public class AdminUserLoginRecordController {
    @Resource
    private UserLoginRecordService userLoginRecordService;

    @ApiOperation("获取会员登录日志列表")
    @GetMapping("/listTop50/{userId}")
    public R listTop50(
            @ApiParam(value = "用户id",required = true)
            @PathVariable("userId") Long userId){
        List<UserLoginRecord> userLoginRecordList=userLoginRecordService.listTop50(userId);
        return R.ok().data("list",userLoginRecordList);
    }

}
