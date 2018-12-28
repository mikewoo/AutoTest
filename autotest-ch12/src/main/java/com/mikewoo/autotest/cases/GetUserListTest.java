package com.mikewoo.autotest.cases;

import com.google.gson.Gson;
import com.mikewoo.autotest.common.ResponseData;
import com.mikewoo.autotest.config.TestConfig;
import com.mikewoo.autotest.domain.GetUserListCase;
import com.mikewoo.autotest.mapper.GetUserListCaseMapper;
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
public class GetUserListTest {

    @Test(dependsOnGroups = "loginTrue", description = "获取用户列表（性别为男）接口，依赖于用户成功登录")
    public void getUserList() throws IOException {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        GetUserListCaseMapper getUserListCaseMapper = sqlSession.getMapper(GetUserListCaseMapper.class);
        GetUserListCase getUserListCase = getUserListCaseMapper.selectByPrimaryKey(1);

        log.info("getUserListCase = {}", getUserListCase);
        log.info("getUserListUrl = {}", TestConfig.getUserListUrl);

        ResponseData responseData = getUserListTest(getUserListCase);

        Assert.assertNotEquals(null, responseData.getData());

        sqlSession.close();
    }

    private ResponseData getUserListTest(GetUserListCase getUserListCase) throws IOException {
        // 构造POST请求
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);

        // 构造请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("username", getUserListCase.getUsername());
        params.put("age", getUserListCase.getAge());
        params.put("sex", getUserListCase.getSex());
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
        log.info("执行{}请求，响应结果：{}", TestConfig.getUserListUrl, result);

        return gson.fromJson(result, ResponseData.class);
    }
}
