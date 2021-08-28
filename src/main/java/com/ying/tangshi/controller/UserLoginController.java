package com.ying.tangshi.controller;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.oracle.tools.packager.Log;
import com.ying.tangshi.entity.User;
import com.ying.tangshi.mapper.UserMapper;
import com.ying.tangshi.service.UserService;
import com.ying.tangshi.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Map;

/**
 * 2021/8/27 - 11:36 上午
 * Lian-Ying
 **/
@Controller
@Slf4j
@RequestMapping("/a")
public class UserLoginController extends BaseController{


    @Autowired
    UserService userService;




    /**
     *退出的时候是请求，主要是用于退出
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut() {
        return "/schoolPage/loginUser";
    }

    /**
     *返回页面，登录页面
     * @return
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "/schoolPage/loginUser";
    }

    /**
     * ajax请求登录
     * @param userNumber
     * @param userPassword
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginAjax", method = RequestMethod.POST)
    public Object login(@RequestParam(value = "userNumber") String userNumber, @RequestParam(value = "userPassword") String userPassword, HttpServletRequest request) {

        ResultUtils resultUtils = new ResultUtils();

        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        //进行验证，这里可以捕获异常，然后返回对应信息

        Map map = userService.userLogin(userNumber, userPassword);
//        User userInfo =(User)map.get("userInfo");
        int userLoginFlag = (Integer)map.get("userLoginFlag");

        if (userLoginFlag==2){
            /////session存用户信息
            request.getSession().setAttribute("userInfo",map.get("userInfo"));
            try {
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userNumber, userPassword);

                subject.login(usernamePasswordToken);

            } catch (UnknownAccountException e) {
                log.error("用户名不存在！", e);
//            return resultUtils.R("用户不存在","0",0,null,null);
            } catch (AuthenticationException e) {
                log.error("账号或密码错误！", e);
//            return resultUtils.R("账号或密码错误","1",1,null,null);

            } catch (AuthorizationException e) {
                log.error("没有权限！", e);
                return resultUtils.R("没有权限",0,0,null,null);

            }
        }


        return map;
    }

    /**
     * 返回页面、首页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "/schoolPage/userPages/index";
    }


    /**
     * 错误页面
     * @return
     */
    @RequestMapping(value = "/error")
    public String error() {
        return "error";
    }

    /**
     * 没有权限的，返回的json
     * @return
     */
    @RequestMapping(value = "/permissionError")
    @ResponseBody
    public Object permissionError() {
        Map map = resultUtils.R("没有权限", -1);
        return map;
    }






    @RequiresRoles("student")
    @RequiresPermissions("level1")
    @RequestMapping(value = "/create1",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String create1() {
        return "有1权限";
    }

    @RequiresRoles("student")
    @RequiresPermissions("level2")
    @RequestMapping(value = "/create2",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String create2() {
        return "有2权限";
    }


}
