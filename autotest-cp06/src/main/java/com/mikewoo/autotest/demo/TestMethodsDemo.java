package com.mikewoo.autotest.demo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * @author Eric Gui
 * @date 2018/12/24
 */
public class TestMethodsDemo {

    @Test
    public void test1() {
        Assert.assertEquals(1, 2);
    }

    @Test
    public void test2() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void test3() {
        Assert.assertEquals("aaa", "aaa");
    }

    @Test
    public void test4() {
        Reporter.log("这是自己写的日志");
        throw new RuntimeException("自定义运行时异常");
    }
}
