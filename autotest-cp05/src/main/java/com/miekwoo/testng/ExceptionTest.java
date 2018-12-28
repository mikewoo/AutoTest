package com.miekwoo.testng;

import org.testng.annotations.Test;

/**
 * @author Eric Gui
 * @date 2018/12/24
 */
public class ExceptionTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed() {
        System.out.println("runTimeExceptionFailed");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess() {
        System.out.println("runTimeExceptionSuccess");
        throw new RuntimeException();
    }
}
