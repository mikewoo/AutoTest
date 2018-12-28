package com.mikewoo.autotest.get;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Eric Gui
 * @date 2018/12/25
 */
public class CookiesForGet {

    private String url;

    private String cookiesGetUri;

    private String getWithCookiesUri;

    private CookieStore cookieStore;

    private ResourceBundle bundle;

    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("server.url");
        cookiesGetUri = bundle.getString("cookies.get.uri");
        getWithCookiesUri = bundle.getString("get.with.cookies");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        // 创建HttpClient上下文
        HttpClientContext context = HttpClientContext.create();

        // 构造一个get请求
        HttpGet get = new HttpGet(this.url + cookiesGetUri);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(get, context);

        // 打印响应信息
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

        // 获取cookie信息
        this.cookieStore = context.getCookieStore();
        List<Cookie> cookieList = this.cookieStore.getCookies();
        for (Cookie cookie : cookieList) {
            System.out.println("cookie name = " + cookie.getName() + ", cookie value = " + cookie.getValue());
        }
    }

    @Test(dependsOnMethods = "testGetCookies")
    public void testGetWithCookies() throws IOException {
        // 构造请求地址
        String getUrl = url + getWithCookiesUri;

        // 声明HttpClient对象，用于执行请求
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        // 设置cookies信息
        CloseableHttpClient client = clientBuilder.setDefaultCookieStore(this.cookieStore).build();

        // 构造get请求
        HttpGet get = new HttpGet(getUrl);

        // 执行get请求
        HttpResponse response = client.execute(get);

        // 获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statusCode);
        if (statusCode == 200) {
            // 打印响应信息
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }
}
