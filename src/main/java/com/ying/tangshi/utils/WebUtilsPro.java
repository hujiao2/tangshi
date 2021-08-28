package com.ying.tangshi.utils;

import cn.hutool.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class WebUtilsPro {
 
    /**
     * 是否是Ajax请求
     *
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {
            System.out.println("----------------"+requestType);
            return true;
        } else {
            return false;
        }
    }
    /**
     * 输出JSON
     */
    public void writeJson(Map<String, Object> resp, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("json", resp);
            out.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}