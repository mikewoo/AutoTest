package com.mikewoo.autotest.utils;

import com.mikewoo.autotest.domain.TestInterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Eric Gui
 * @date 2018/12/27
 */
public class TestUrlGeneratorUtil {

    private static ResourceBundle bundle = ResourceBundle.getBundle("testconfig", Locale.CHINA);

    public static String getTestUrl(TestInterfaceName interfaceName) {
        String baseUrl = bundle.getString("test.url");

        String uri = "";
        switch (interfaceName) {
            case LOGIN:
                uri = bundle.getString("login.uri");
                break;
            case ADDUSER:
                uri = bundle.getString("user.add.uri");
                break;
            case GETUSERINFO:
                uri = bundle.getString("user.info.get.uri");
                break;
            case UPDATEUSERINFO:
                uri = bundle.getString("user.info.update.uri");
                break;
            case GETUSERLIST:
                uri = bundle.getString("user.list.get.uri");
                break;
                default:
                    break;
        }

        return baseUrl + uri;
    }
}
