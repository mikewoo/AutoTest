package com.miekwoo.testng;

import org.testng.annotations.Test;

/**
 * @author Eric Gui
 * @date 2018/12/24
 */
public class TimeOutTest {

    @Test(timeOut = 3000) // 单位为毫秒值
    public void timeOutTestSuccess() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test(timeOut = 2000) // 单位为毫秒值
    public void timeOutTestFailed() throws InterruptedException {
        Thread.sleep(3000);
    }
}
