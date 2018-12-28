package com.mikewoo.autotest.cases;

import com.google.gson.Gson;
import com.mikewoo.autotest.common.ResponseData;
import com.mikewoo.autotest.config.TestConfig;
import com.mikewoo.autotest.domain.LoginCase;
import com.mikewoo.autotest.domain.TestInterfaceName;
import com.mikewoo.autotest.mapper.LoginCaseMapper;
import com.mikewoo.autotest.utils.SqlSessionUtil;
import com.mikewoo.autotest.utils.TestUrlGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录测试
 * @author Eric Gui
 * @date 2018/12/27
 */
@Slf4j
public class LoginTest {

    @BeforeTest(groups = "loginTrue", description = "用户登录接口测试准备工作")
    public void beforeTest() {
        TestConfig.loginUrl = TestUrlGeneratorUtil.getTestUrl(TestInterfaceName.LOGIN);
        TestConfig.addUserUrl = TestUrlGeneratorUtil.getTestUrl(TestInterfaceName.ADDUSER);
        TestConfig.getUserInfoUrl = TestUrlGeneratorUtil.getTestUrl(TestInterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = TestUrlGeneratorUtil.getTestUrl(TestInterfaceName.GETUSERLIST);
        TestConfig.updateUserInfoUrl = TestUrlGeneratorUtil.getTestUrl(TestInterfaceName.UPDATEUSERINFO);
        TestConfig.context = HttpClientContext.create();
        TestConfig.cookieStore = TestConfig.context.getCookieStore();
        TestConfig.httpClient = HttpClientBuilder.create().setDefaultCookieStore(TestConfig.cookieStore).build();
    }

    @Test(groups = "loginTrue", description = "用户登录成功接口")
    public void loginTrue() throws IOException {
        // 获取测试用例
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        LoginCaseMapper loginCaseMapper = sqlSession.getMapper(LoginCaseMapper.class);
        LoginCase loginCase = loginCaseMapper.selectByPrimaryKey(1);
        log.info("loginCase = {}", loginCase.toString());
        log.info("loginUrl = {}", TestConfig.loginUrl);
        sqlSession.close();

        // 执行测试用例，获取测试结果
        ResponseData result = getLoginTestResult(loginCase);

        // 处理测试结果，判断测试结果值是否符合预期
        Assert.assertEquals(loginCase.getExpected(), String.valueOf(result.getData()));
    }

    @Test(groups = "loginFalse", description = "用户登录失败接口")
    public void loginFalse() throws IOException {
        // 获取测试用例
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        LoginCaseMapper loginCaseMapper = sqlSession.getMapper(LoginCaseMapper.class);
        LoginCase loginCase = loginCaseMapper.selectByPrimaryKey(2);
        log.info("loginCase = {}", loginCase.toString());
        log.info("loginUrl = {}", TestConfig.loginUrl);

        // 执行测试用例
        ResponseData result = getLoginTestResult(loginCase);

        // 处理测试结果，判断测试结果值是否符合预期
        Assert.assertEquals(loginCase.getExpected(), String.valueOf(result.getData()));

        sqlSession.close();
    }

    private ResponseData getLoginTestResult(LoginCase loginCase) throws IOException {
        // 构造POST请求
        HttpPost post = new HttpPost(TestConfig.loginUrl);

        // 构造请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("username", loginCase.getUsername());
        params.put("password", loginCase.getPassword());
        Gson gson = new Gson();
        StringEntity entity = new StringEntity(gson.toJson(params), "utf-8");

        // 设置请求参数
        post.setEntity(entity);

        // 设置头信息
        post.setHeader("content-type", "application/json");

        // 执行POST请求
        CloseableHttpResponse response = TestConfig.httpClient.execute(post, TestConfig.context);

        // 获取响应结果
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        log.info("执行{}请求，响应结果： {}", TestConfig.loginUrl, result);

        return gson.fromJson(result, ResponseData.class);
    }
}
