package com.mikewoo.autotest.controller;

import com.mikewoo.autotest.common.ResponseData;
import com.mikewoo.autotest.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author Eric Gui
 * @date 2018/12/25
 */
@RestController
@Api(value = "/", description = "Springboot POST方法测试接口")
public class PostCookiesController {

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "登录成功后返回cookie信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true),
    })
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        if ("eric".equals(username) && "123456".equals(password)) {
            Cookie cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "恭喜，登陆成功！！！";
        }
        return "用户名或者密码不正确";
    }

    @PostMapping("/user")
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true)
    public ResponseData findUser(HttpServletRequest request, @RequestBody User user) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return ResponseData.build(400, "用户名身份不合法");
        }
        for (Cookie cookie : cookies) {
            if ("login".equals(cookie.getName()) && !"true".equals(cookie.getValue())) {
                return ResponseData.build(400, "用户未登录");
            }
            if ("login".equals(cookie.getName()) && "true".equals(cookie.getValue()) && "eric".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
                user.setName("Eric Gui");
                user.setAge(29);
                user.setSex("male");
                return ResponseData.ok(user);
            }
        }
        return ResponseData.build(400, "用户名或者密码不正确");
    }
}
