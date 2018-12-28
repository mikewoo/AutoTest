package com.miekwoo.testng;

import org.testng.annotations.*;

/**
 * @author Eric Gui
 * @date 2018/12/24
 */
public class BasicAnnotation {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("这是在测试方法之前运行的");
    }

    @Test
    public void testCase1() {
        System.out.println("这是测试用例1");
    }

    @Test
    public void testCase2() {
        System.out.println("这是测试用例2");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("这是在测试方法执行之后运行的");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("在类运行之前执行的方法");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("在类运行之后执行的方法");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("在测试类运行之前执行");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("在测试类运行之后执行");
    }

}
