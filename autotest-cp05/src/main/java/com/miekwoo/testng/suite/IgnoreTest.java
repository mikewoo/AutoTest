package com.miekwoo.testng.suite;

import org.testng.annotations.Test;

/**
 * @author Eric Gui
 * @date 2018/12/24
 */
public class IgnoreTest {

    @Test
    public void ignoreTest01() {
        System.out.println("ignoreTest01");
    }

    @Test(enabled = false)
    public void ignoreTest02() {
        System.out.println("ignoreTest02");
    }

    @Test(enabled = true)
    public void ignoreTest03() {
        System.out.println("ignoreTest03");
    }
}
