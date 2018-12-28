package com.mikewoo.autotest.config;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author Eric Gui
 * @date 2018/12/27
 */
public class TestConfig {

    public static String loginUrl;

    public static String addUserUrl;

    public static String getUserInfoUrl;

    public static String updateUserInfoUrl;

    public static String getUserListUrl;

    public static CloseableHttpClient httpClient;

    public static HttpClientContext context;

    public static CookieStore cookieStore;
}
