package com.miekwoo.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @author Eric Gui
 * @date 2018/12/24
 */
public class DataProviderTest {

    @Test(dataProvider = "data")
    public void dateProviderTest(String name, Integer age) {
        System.out.println("dateProviderTest: name = " + name + ", age = " + age);
    }

    @Test(dataProvider = "methodData")
    public void dateProviderMethodTest1(String name, Integer age) {
        System.out.println("dateProviderMethodTest1: name = " + name + ", age = " + age);
    }

    @Test(dataProvider = "methodData")
    public void dateProviderMethodTest2(String name, String nikename) {
        System.out.println("dateProviderMethodTest1: name = " + name + ", nikename = " + nikename);
    }

    @DataProvider(name = "data")
    public Object[][] providerData() {
        Object[][] objects = new Object[][] {
                {"Eric", 28},
                {"Phantom", 29},
                {"Skye", 30}
        };
        return objects;
    }

    @DataProvider(name = "methodData")
    public Object[][] providerMethodData(Method method) {
        Object[][] objects = null;
        if ("dateProviderMethodTest1".equals(method.getName())) {
            objects = new Object[][] {
                    {"Eric", 28},
                    {"Phantom", 29},
                    {"Skye", 30}
            };
        } else if ("dateProviderMethodTest2".equals(method.getName())) {
            objects = new Object[][] {
                    {"Eric", "Eric123"},
                    {"Phantom", "Phantom123"},
                    {"Skye", "Skye123"}
            };
        }
        return objects;
    }
}
