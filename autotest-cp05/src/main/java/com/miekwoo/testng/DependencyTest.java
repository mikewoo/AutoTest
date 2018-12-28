package com.miekwoo.testng;

import org.testng.annotations.Test;

/**
 * @author Eric Gui
 * @date 2018/12/24
 */
public class DependencyTest {

    @Test
    public void test1() {
        System.out.println("test1");
        // throw new RuntimeException();
    }

    @Test(dependsOnMethods = "test1")
    public void test2() {
        System.out.println("test2");
    }
}
