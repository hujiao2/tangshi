package com.ying.tangshi.utils;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 2021/3/31 - 10:17 下午
 * Lian-Ying
 **/
@Repository
public class ResultUtils {
    public Map R(String msg,int code,Object flag,Object data,Object other){
        Map<String,Object> result = new HashMap<>();
        result.put("msg",msg);/////信息
        result.put("code",code);//////状态码
        result.put("flag",flag);////标志
        result.put("data",data);/////数据
        result.put("other",other);//////其他
        return result;
    }
    public Map R(String msg,int code,Object data){
        Map<String,Object> result = new HashMap<>();
        result.put("msg",msg);/////信息
        result.put("code",code);//////状态码
        result.put("data",data);/////数据
        return result;
    }
    public Map R(String msg,int code){
        Map<String,Object> result = new HashMap<>();
        result.put("msg",msg);/////信息
        result.put("code",code);//////状态码
        return result;
    }
    public Map R(){
        Map<String,Object> result = new HashMap<>();

        return result;
    }
}
