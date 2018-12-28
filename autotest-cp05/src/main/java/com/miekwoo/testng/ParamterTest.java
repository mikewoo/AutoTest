package com.miekwoo.testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Eric Gui
 * @date 2018/12/24
 */
public class ParamterTest {

    @Test
    @Parameters(value = {"name", "age"})
    public void paramTest1(String name, Integer age) {
        System.out.println("name = " + name + ", age = " + age);
    }
}
