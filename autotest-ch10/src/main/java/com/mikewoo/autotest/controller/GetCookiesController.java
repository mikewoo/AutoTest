package com.mikewoo.autotest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Eric Gui
 * @date 2018/12/25
 */
@RestController
@Api(value = "/", description = "Springboot GET方法测试接口")
public class GetCookiesController {

    /**
     * 通过{@link HttpServletResponse}设置cookie信息
     *
     * @param response http响应对象
     * @return 响应结果
     */
    @GetMapping("/get_cookies")
    @ApiOperation(value = "获取cookie信息", httpMethod = "GET")
    public String getCookies(HttpServletResponse response) {
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "获取cookie信息响应！！！";
    }

    /**
     * 通过{@link HttpServletRequest}获取cookie信息
     *
     * @param request http请求对象
     * @return 响应结果
     */
    @GetMapping("/get_demo_with_cookies")
    @ApiOperation(value = "通过cookie信息访问", httpMethod = "GET", notes = "必须携带key为login的cookie信息才能访问")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "必须携带cookie信息访问！！！";
        }
        for (Cookie cookie : cookies) {
            if ("login".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                return "携带cookie信息请求响应！！！";
            }
        }
        return "必须携带cookie信息访问！！！";
    }

    /**
     * 通过{@link RequestParam}获取url中key=value形式的参数
     *
     * @param start 起始位置
     * @param end   结束位置
     * @return 商品列表
     */
    @GetMapping("/get_commodity_list")
    @ApiOperation(value = "获取商品列表", httpMethod = "GET", notes = "参数为列表起止位置")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "start", value = "起始位置", required = true),
            @ApiImplicitParam(paramType = "query", name = "end", value = "结束位置", required = true)
    })
    public Map<String, Object> getCommodityList(@RequestParam Integer start, @RequestParam Integer end) {
        Map<String, Object> list = new HashMap<>();
        list.put("干脆面", 1500);
        list.put("火鸡面", 2500);
        list.put("火腿肠", 1000);
        list.put("凤爪", 1200);
        return list;
    }

    /**
     * 通过{@link PathVariable}获得路径参数
     *
     * @param start 起始位置
     * @param end   结束位置
     * @return 用户列表
     */
    @GetMapping("/get_user_list/{start}/{end}")
    @ApiOperation(value = "获取用户列表", httpMethod = "GET", notes = "参数为列表起止位置")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "start", value = "起始位置", required = true),
            @ApiImplicitParam(paramType = "path", name = "end", value = "结束位置", required = true)
    })
    public Map<String, Object> getUserList(@PathVariable Integer start, @PathVariable Integer end) {
        Map<String, Object> list = new HashMap<>();
        list.put("user001", "曹孟德");
        list.put("user002", "吕奉先");
        list.put("user03", "诸葛孔明");
        return list;
    }
}
