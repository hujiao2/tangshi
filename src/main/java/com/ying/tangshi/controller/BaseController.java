package com.ying.tangshi.controller;


import cn.hutool.json.JSONObject;
import com.ying.tangshi.utils.ResultUtils;
import com.ying.tangshi.utils.WebUtilsPro;
import org.apache.ibatis.annotations.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    @Autowired
    ResultUtils resultUtils;

//    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
//    public Object authorizationException(HttpServletRequest request, HttpServletResponse response) {
//        if (WebUtilsPro.isAjaxRequest(request)) {
//            //权限认证异常且输出JSON
//            Map map = resultUtils.R("没有该权限", -1);
//
//            return map;
//        } else {
//            return null;
//        }
//    }

}
