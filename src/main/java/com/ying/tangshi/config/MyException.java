package com.ying.tangshi.config;


import com.ying.tangshi.utils.ResultUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 2021/8/29 - 12:48 上午
 * Lian-Ying
 **/
@ControllerAdvice
public class MyException {

    @Autowired
    ResultUtils resultUtils;

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public Object authorizationException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {

        Map map = resultUtils.R("没有权限", -1);
        return map;

    }

//    @ExceptionHandler(value = NotFoundException.class)
//    public void NotFoundException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
//
//        response.sendRedirect("/a/error");
//    }

}
