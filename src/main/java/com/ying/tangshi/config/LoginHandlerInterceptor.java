package com.ying.tangshi.config;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2021/8/27 - 9:27 上午
 * Lian-Ying
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("userInfo");//获取session对象
        if (loginUser == null) {
            System.out.println("请先登录");
            request.setAttribute("msg", "没有权限，请先登录");
            /////跳转到页面.html
            request.getRequestDispatcher("/schoolPage/loginUser.html").forward(request, response);//可以用来把当前request传递到该资源，参数为请求地址 或者把新的资源包括到当前响应中 类似model 传递给login.html
            return false;
        } else {

            return true;
        }

    }
}
