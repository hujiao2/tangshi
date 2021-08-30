/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ying.tangshi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtils {

    /**
     * 获取当前系统时间，毫秒级
     * 
     * @return
     */
    public static long getCurrentTimesInMillSeconds() {

        return System.currentTimeMillis();
    }
    
    
    /**
     * @param format 时间格式化标准  eg YYYYMM
     * @return
     */
    public static String getCurrentTime(String format) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    public static void main(String[] args) {
       System.out.println( getCurrentTime("yyyyMMddHHmmss"));
    }
}
