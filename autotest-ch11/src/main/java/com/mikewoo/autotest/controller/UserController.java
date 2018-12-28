package com.mikewoo.autotest.controller;

import com.mikewoo.autotest.common.ResponseData;
import com.mikewoo.autotest.domian.User;
import com.mikewoo.autotest.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eric Gui
 * @date 2018/12/25
 */
@RestController
@Api(value = "/user", description = "用户操作接口")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user")
    @ApiOperation(value = "查询用户数量", httpMethod = "GET")
    public ResponseData getUserCount() {
        Long count = userMapper.getUserCount();
        return ResponseData.ok(count);
    }

    @PostMapping(value = "/user")
    @ApiOperation(value = "新增用户", notes = "用户ID为自增字段", httpMethod = "POST")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true)
    public ResponseData updateUser(@RequestBody User user) {
        userMapper.insert(user);
        return ResponseData.ok(user);
    }
}
