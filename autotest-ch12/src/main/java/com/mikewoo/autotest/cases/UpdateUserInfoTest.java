package com.mikewoo.autotest.cases;

import com.google.gson.Gson;
import com.mikewoo.autotest.common.ResponseData;
import com.mikewoo.autotest.config.TestConfig;
import com.mikewoo.autotest.domain.UpdateUserInfoCase;
import com.mikewoo.autotest.domain.User;
import com.mikewoo.autotest.mapper.UpdateUserInfoCaseMapper;
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
import java.util.HashMap;
import java.util.Map;

/**
 * @author Eric Gui
 * @date 2018/12/27
 */
@Slf4j
public class UpdateUserInfoTest {

    @Test(dependsOnGroups = "loginTrue", description = "更新用户信息接口，依赖于用户成功登录")
    public void updateUserInfo() throws IOException {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UpdateUserInfoCaseMapper updateUserInfoCaseMapper = sqlSession.getMapper(UpdateUserInfoCaseMapper.class);
        UpdateUserInfoCase updateUserInfoCase = updateUserInfoCaseMapper.selectByPrimaryKey(1);

        log.info("updateUserInfoCase = {}", updateUserInfoCase);
        log.info("updateUserInfoUrl = {}", TestConfig.updateUserInfoUrl);

        ResponseData responseData = getUpdateUserInfoTestResult(updateUserInfoCase);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(updateUserInfoCase.getUserId());
        log.info("user：{}", user);
        Assert.assertEquals(updateUserInfoCase.getExpected(), String.valueOf(responseData.getData()));

        sqlSession.close();
    }


    @Test(dependsOnGroups = "loginTrue", description = "删除用户接口，依赖于用户成功登录")
    public void deleteUser() throws IOException {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UpdateUserInfoCaseMapper updateUserInfoCaseMapper = sqlSession.getMapper(UpdateUserInfoCaseMapper.class);
        UpdateUserInfoCase updateUserInfoCase = updateUserInfoCaseMapper.selectByPrimaryKey(2);
        log.info("updateUserInfoCase = {}", updateUserInfoCase);
        log.info("updateUserInfoUrl = {}", TestConfig.updateUserInfoUrl);

        ResponseData responseData = getUpdateUserInfoTestResult(updateUserInfoCase);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(updateUserInfoCase.getUserId());
        log.info("user：{}", user);
        Assert.assertEquals(updateUserInfoCase.getExpected(), String.valueOf(responseData.getData()));

        sqlSession.close();
    }

    private ResponseData getUpdateUserInfoTestResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        // 构造POST请求
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);

        // 构造请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("id", updateUserInfoCase.getUserId());
        params.put("username", updateUserInfoCase.getUsername());
        params.put("sex", updateUserInfoCase.getSex());
        params.put("age", updateUserInfoCase.getAge());
        params.put("permission", updateUserInfoCase.getPermission());
        params.put("isDel", updateUserInfoCase.getIsDel());

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
        log.info("执行{}请求，响应结果：{}", TestConfig.updateUserInfoUrl, result);

        return gson.fromJson(result, ResponseData.class);
    }
}
