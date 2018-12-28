package com.mikewoo.autotest.post;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
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
public class CookiesForPost {

    private String url;

    private String cookiesGetUri;

    private String postWithCookiesUri;

    private CookieStore cookieStore;

    private ResourceBundle bundle;

    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("server.url");
        cookiesGetUri = bundle.getString("cookies.get.uri");
        postWithCookiesUri = bundle.getString("post.with.cookies");
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
    public void testPostWithCookies() throws IOException {
        // 构造请求地址
        String postUrl = url + postWithCookiesUri;

        // 声明HttpClient对象，用于执行请求
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        // 设置cookies信息
        CloseableHttpClient client = clientBuilder.setDefaultCookieStore(this.cookieStore).build();

        // 构造post请求
        HttpPost post = new HttpPost(postUrl);

        // 添加参数
        JSONObject params = new JSONObject();
        params.put("name", "eric");
        params.put("age", "18");
        StringEntity entity = new StringEntity(params.toString(), "utf-8");
        post.setEntity(entity);

        // 设置请求头信息
        post.setHeader("content-type", "application/json");

        // 执行post请求
        CloseableHttpResponse response = client.execute(post);

        // 获取请求结果
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

        // 处理请求结果
        JSONObject resultJson = new JSONObject(result);
        String name = (String) resultJson.get("name");
        String status = (String) resultJson.get("status");
        Assert.assertEquals("eric", name);
        Assert.assertEquals("success", status);
    }
}
