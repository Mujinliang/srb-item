package com.nawiew.srb.core.controller.admin;


import com.nawiew.common.exception.Assert;
import com.nawiew.common.exception.BusinessException;
import com.nawiew.common.result.R;
import com.nawiew.common.result.ResponseEnum;
import com.nawiew.srb.core.pojo.entity.IntegralGrade;
import com.nawiew.srb.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.core.controller.admin
 * @ClassName:AdminIntegralGradeController
 * @Description:
 * @date 2021/7/2 19:25
 */
//跨域问题
/*@CrossOrigin*/
@RestController
@RequestMapping("/admin/core/integralGrade")
@Api(tags = "积分等级管理")
@Slf4j
public class AdminIntegralGradeController {
    @Resource
    private IntegralGradeService integralGradeService;

    /**
     * 查询积分列表
     * @return
     */
    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public R listAll(){
      /*  try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        log.info("hi i'm helen");
        log.warn("warning!!!");
        log.error("it's a error");
        return R.ok().data("list",integralGradeService.list());
    }


    /**
     * 根据id逻辑删除
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "根据id删除积分等级",notes = "逻辑删除")
    public R removeById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Long id){
        boolean result = integralGradeService.removeById(id);
        if (result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    /**
     * 新增数据
     * @param integralGrade
     * @return
     */
    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(
            @ApiParam(value = "积分等级对象",required = true)
            @RequestBody IntegralGrade integralGrade){
        Assert.notNull(integralGrade.getBorrowAmount(), ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        boolean result = integralGradeService.save(integralGrade);
        if (result){
            return  R.ok().message("保存成功");
        }else {
            return R.error().message("保存失败");
        }
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取积分等级")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据id",required = true,example = "1")
            @PathVariable Long id){
        IntegralGrade integralGrade = integralGradeService.getById(id);
        if (integralGrade!=null){
            return R.ok().data("record",integralGrade);
        }else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(value = "积分等级对象",required = true)
            @RequestBody IntegralGrade integralGrade){
        boolean result = integralGradeService.updateById(integralGrade);
        if (result){
            return R.ok().message("修改成功");
        }else {
            return R.error().message("修改失败");
        }
    }























}
