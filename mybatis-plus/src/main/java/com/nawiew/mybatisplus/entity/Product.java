package com.nawiew.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @author nawiew
 * @packageName:com.nawiew.mybatisplus.entity
 * @ClassName:Product
 * @Description:
 * @date 2021/7/1 18:30
 */
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version
    private Integer version;
}
