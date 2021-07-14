package com.nawiew.mybatisplus;


import com.nawiew.mybatisplus.entity.Product;
import com.nawiew.mybatisplus.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author nawiew
 * @packageName:com.nawiew.mybatisplus
 * @ClassName:ProductTest
 * @Description:
 * @date 2021/7/1 18:33
 */
@SpringBootTest
public class ProductTest {
    @Resource
    private ProductMapper productMapper;

    @Test
    public void testConcurrentUpdate() {

        //1、小李
        Product p1 = productMapper.selectById(1L);

        //2、小王
        Product p2 = productMapper.selectById(1L);

        //3、小李将价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice() + 50);
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改结果：" + result1);

        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice() - 30);
        int result2 = productMapper.updateById(p2);
        if(result2 == 0){//更新失败，重试
            System.out.println("小王重试");
            //重新获取数据
            p2 = productMapper.selectById(1L);
            //更新
            p2.setPrice(p2.getPrice() - 30);
            productMapper.updateById(p2);
        }
        //最后的结果
        Product p3 = productMapper.selectById(1L);
        System.out.println("最后的结果：" + p3.getPrice());
    }
}
