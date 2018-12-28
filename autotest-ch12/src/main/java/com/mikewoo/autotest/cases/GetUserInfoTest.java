package com.mikewoo.autotest.cases;

import com.google.gson.*;
import com.mikewoo.autotest.common.ResponseData;
import com.mikewoo.autotest.config.TestConfig;
import com.mikewoo.autotest.domain.GetUserInfoCase;
import com.mikewoo.autotest.domain.User;
import com.mikewoo.autotest.mapper.GetUserInfoCaseMapper;
import com.mikewoo.autotest.mapper.UserMapper;
import com.mikewoo.autotest.utils.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

/**
 * 获取用户信息测试
 * @author Eric Gui
 * @date 2018/12/27
 */
@Slf4j
public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue", description = "获取用户信息（id为1）接口，依赖于用户成功登录")
    public void getUserInfo() throws IOException {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        GetUserInfoCaseMapper getUserInfoCaseMapper = sqlSession.getMapper(GetUserInfoCaseMapper.class);
        GetUserInfoCase getUserInfoCase = getUserInfoCaseMapper.selectByPrimaryKey(1);
        log.info("getUserInfoCase = {}", getUserInfoCase);
        log.info("getUserInfoUrl = {}", TestConfig.getUserInfoUrl);

        ResponseData responseData = getUserInfoTestResult(getUserInfoCase);


        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(getUserInfoCase.getUserId());

        log.info("response data: {}", responseData.toString());
        Assert.assertNotEquals(null, responseData.getData());

        sqlSession.close();
    }

    private ResponseData getUserInfoTestResult(GetUserInfoCase getUserInfoCase) throws IOException {
        // 构造POST请求
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);

        // 构造请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("id", getUserInfoCase.getUserId());
        Gson gson = new Gson();
        StringEntity entity = new StringEntity(gson.toJson(params), "utf-8");

        // 设置请求参数
        post.setEntity(entity);

        // 设置头信息
        post.setHeader("content-type", "application/json");

        // 执行POST请求
        CloseableHttpResponse response = TestConfig.httpClient.execute(post, TestConfig.context);

        // 获取响应结果
        String result = EntityUtils.toString(response.getEntity());
        log.info("执行{}请求，响应结果：{}", TestConfig.addUserUrl, result);

        return gson.fromJson(result, ResponseData.class);
    }
}
