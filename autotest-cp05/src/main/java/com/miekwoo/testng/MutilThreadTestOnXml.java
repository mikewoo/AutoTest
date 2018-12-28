package com.miekwoo.testng;

import org.testng.annotations.Test;

/**
 * @author Eric Gui
 * @date 2018/12/24
 */
public class MutilThreadTestOnXml {

    @Test
    public void test1() {
        System.out.printf("Thread Id: %s%n", Thread.currentThread().getId());
    }

    @Test
    public void test2() {
        System.out.printf("Thread Id: %s%n", Thread.currentThread().getId());
    }

    @Test
    public void test3() {
        System.out.printf("Thread Id: %s%n", Thread.currentThread().getId());
    }
}
