package com.miekwoo.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * @author Eric Gui
 * @date 2018/12/24
 */
public class GroupsOnMethod {

    @Test(groups = "server")
    public void test1() {
        System.out.println("server test1");
    }

    @Test(groups = "server")
    public void test2() {
        System.out.println("server test2");
    }

    @Test(groups = "other")
    public void test3() {
        System.out.println("other test3");
    }

    @Test(groups = "other")
    public void test4() {
        System.out.println("other test4");
    }

    @BeforeGroups("server")
    public void beforeGroupsOnServer() {
        System.out.println("beforeGroupsOnServer");
    }


    @AfterGroups("server")
    public void afterGroupsOnServer() {
        System.out.println("afterGroupsOnServer");
    }

    @BeforeGroups("server")
    public void beforeGroupsOnOther() {
        System.out.println("beforeGroupsOnOther");
    }


    @AfterGroups("server")
    public void afterGroupsOnOther() {
        System.out.println("beforeGroupsOnOther");
    }
}
