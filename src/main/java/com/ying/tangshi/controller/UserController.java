package com.ying.tangshi.controller;


import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ying.tangshi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import com.ying.tangshi.controller.BaseController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ts
 * @since 2021-08-25
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;



    @RequestMapping("/userLogin")
    @ResponseBody
    public Object userLogin(@RequestParam(value="userNumber") String userNumber, @RequestParam(value="userPassword") String userPassword) {


        return userService.userLogin(userNumber, userPassword);/////用户登录验证;


    }

    @RequestMapping("/userLoginH5")
    public String userLoginH5(){

        return "index";
    }





}

