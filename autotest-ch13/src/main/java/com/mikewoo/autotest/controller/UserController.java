package com.mikewoo.autotest.controller;

import com.mikewoo.autotest.common.ResponseData;
import com.mikewoo.autotest.domain.User;
import com.mikewoo.autotest.domain.UserExample;
import com.mikewoo.autotest.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * @author Eric Gui
 * @date 2018/12/27
 */
@Slf4j
@RestController
@Api(value = "v1", description = "用户管理接口")
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "用户登录接口", httpMethod = "POST")
    @PostMapping(value = "/login")
    public ResponseData login(HttpServletResponse response, @RequestBody User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        criteria.andPasswordEqualTo(user.getPassword());
        long count = userMapper.countByExample(example);
        if (count > 0) {
            Cookie cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            log.info("{} 登陆成功", user.getUsername());
            return ResponseData.ok(true);
        }

        log.info("登录失败");
        return ResponseData.build(400, "用户名或者密码不正确", false);
    }

    @ApiOperation(value = "添加用户接口", httpMethod = "POST")
    @PostMapping(value = "/user/add")
    public ResponseData addUser(HttpServletRequest request, @RequestBody User user) {
        boolean verify = verifyCookies(request);
        if (verify) {
            User tmpUser = new User();
            tmpUser.setUsername(user.getUsername());
            List<User> users = userMapper.getUserInfo(tmpUser);
            if (Objects.nonNull(users) && !users.isEmpty()) {
                return ResponseData.build(400, "用户名[" + user.getUsername() + "]已存在", false);
            }
            int count = userMapper.insertSelective(user);
            if (count > 0) {
                return ResponseData.ok(true);
            }
        } else {
            return ResponseData.build(400, "用户名未登录", false);
        }

        return ResponseData.build(400, "添加用户失败", false);
    }

    @ApiOperation(value = "获取用户（列表）信息接口", httpMethod = "POST")
    @RequestMapping(value = "/user")
    public ResponseData getUserInfo(HttpServletRequest request, @RequestBody User user) {
        boolean verify = verifyCookies(request);
        if (verify) {
            List<User> users = userMapper.getUserInfo(user);
            if (Objects.nonNull(users)) {
                return ResponseData.ok(users);
            }
        } else {
            return ResponseData.build(400, "用户名未登录", null);
        }

        return ResponseData.build(400, "获取用户（列表）信息失败", null);

    }

    @ApiOperation(value = "更新用户信息接口", httpMethod = "POST")
    @PostMapping("/user/update")
    public ResponseData updateUser(HttpServletRequest request, @RequestBody User user) {
        boolean verify = verifyCookies(request);
        if (verify) {
            int count = userMapper.updateByPrimaryKeySelective(user);
            if (count > 0) {
                return ResponseData.ok(true);
            }
        } else {
            return ResponseData.build(400, "用户名未登录", false);
        }

        return ResponseData.build(400, "更新用户信息失败", false);
    }

    private boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            log.info("用户未登录，cookies为空");
            return false;
        }

        for (Cookie cookie : cookies) {
            if ("login".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                log.info("用户已登录，cookie验证通过");
                return true;
            }
        }

        return false;
    }
}
