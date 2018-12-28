package com.mikewoo.autotest.cases;

import com.google.gson.Gson;
import com.mikewoo.autotest.common.ResponseData;
import com.mikewoo.autotest.config.TestConfig;
import com.mikewoo.autotest.domain.AddUserCase;
import com.mikewoo.autotest.domain.User;
import com.mikewoo.autotest.domain.UserExample;
import com.mikewoo.autotest.mapper.AddUserCaseMapper;
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
import java.util.List;
import java.util.Map;

/**
 * @author Eric Gui
 * @date 2018/12/27
 */
@Slf4j
public class AddUserTest {

    @Test(dependsOnGroups = "loginTrue", description = "添加用户接口")
    public void addUser() throws IOException {
        // 获取测试用例
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        AddUserCaseMapper addUserCaseMapper = sqlSession.getMapper(AddUserCaseMapper.class);
        AddUserCase addUserCase = addUserCaseMapper.selectByPrimaryKey(1);
        log.info("addUserCase = {}", addUserCase);
        log.info("addUserUrl = {}", TestConfig.addUserUrl);

        // 执行测试用例，获取测试结果
        ResponseData responseData = getAddUserTestResult(addUserCase);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(addUserCase.getUsername());
        criteria.andPasswordEqualTo(addUserCase.getPassword());
        criteria.andAgeEqualTo(addUserCase.getAge());
        criteria.andSexEqualTo(addUserCase.getSex());
        criteria.andPermissionEqualTo(addUserCase.getPermission());
        criteria.andIsDelEqualTo(addUserCase.getIsDel());
        List<User> users = userMapper.selectByExample(example);
        users.forEach(System.out::println);

        // 处理测试结果，判断测试结果值是否符合预期
        Assert.assertEquals(addUserCase.getExpected(), String.valueOf(responseData.getData()));

        sqlSession.close();
    }

    private ResponseData getAddUserTestResult(AddUserCase addUserCase) throws IOException {
        // 构造POST请求
        HttpPost post = new HttpPost(TestConfig.addUserUrl);

        // 构造请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("username", addUserCase.getUsername());
        params.put("password", addUserCase.getPassword());
        params.put("sex", addUserCase.getSex());
        params.put("age", addUserCase.getAge());
        params.put("permission", addUserCase.getPermission());
        params.put("isDel", addUserCase.getIsDel());
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
