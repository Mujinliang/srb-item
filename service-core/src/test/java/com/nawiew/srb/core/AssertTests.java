package com.nawiew.srb.core;


import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.core
 * @ClassName:AssertTests
 * @Description:
 * @date 2021/7/6 9:03
 */
public class AssertTests {
    @Test
    public void test1(){
        Object o=null;
        if (o==null){
            try {
                throw new IllegalAccessException("用户不存在");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2(){
        Object o=null;
        Assert.notNull(o,"用户不存在");
    }
}
