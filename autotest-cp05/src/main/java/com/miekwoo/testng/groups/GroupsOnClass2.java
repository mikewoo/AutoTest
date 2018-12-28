package com.miekwoo.testng.groups;

import org.testng.annotations.Test;

/**
 * @author Eric Gui
 * @date 2018/12/24
 */
@Test(groups = "student")
public class GroupsOnClass2 {

    public void test1() {
        System.out.println("GroupsOnClass2  test1");
    }

    public void test2() {
        System.out.println("GroupsOnClass2  test2");
    }

    public void test3() {
        System.out.println("GroupsOnClass2  test3");
    }

    public void test4() {
        System.out.println("GroupsOnClass2  test4");
    }
}
