package com.nawiew.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author nawiew
 * @packageName:com.nawiew.mybatisplus.entity
 * @ClassName:User
 * @Description:
 * @date 2021/7/1 10:43
 */
@Data
@TableName(value = "user")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField
    private String name;
    @TableField
    private Integer age;
    @TableField
    private String email;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    @TableField(value = "is_deleted")
    private Integer deleted;
}
