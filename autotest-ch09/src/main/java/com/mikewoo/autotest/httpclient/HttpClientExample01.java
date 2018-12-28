package com.mikewoo.autotest.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author Eric Gui
 * @date 2018/12/25
 */
public class HttpClientExample01 {

    @Test
    public void test1() throws IOException {
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(httpGet);
        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }
}
