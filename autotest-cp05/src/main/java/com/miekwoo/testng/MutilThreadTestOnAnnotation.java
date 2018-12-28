package com.miekwoo.testng;

import org.testng.annotations.Test;

/**
 * @author Eric Gui
 * @date 2018/12/24
 */
public class MutilThreadTestOnAnnotation {

    @Test(invocationCount = 10, threadPoolSize = 3)
    public void mutilThreadTest() {
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }
}
