package com.ying.tangshi.config;


/**
 * 2021/5/9 - 12:44 上午
 * Lian-Ying
 **/
import org.springframework.beans.BeansException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取配置文件
 * @author Zyred
 */
public class PropertiesListenerConfig {

    /**
     * 定义属性集合
     */
    public static Map propertiesMap = new HashMap();

    /**
     * 加载配置文件
     * @param props
     * @throws BeansException
     */
    private static void processProperties(Properties props) throws BeansException {
        propertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            try {
                // PropertiesLoaderUtils的默认编码是ISO-8859-1,在这里转码一下
                propertiesMap.put(keyStr, new String(
                        props.getProperty(keyStr).getBytes("ISO-8859-1"), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (java.lang.Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 加载配置文件
     * @param propertyFileName
     */
    public static void loadAllProperties(String propertyFileName) {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
            processProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据属性获取配置项
     * @param name
     * @return
     */
    public static String getProperty(String name) {
        return propertiesMap.get(name) + "";
    }

    /**
     * 获取所有配置项
     * @return
     */
    public static Map<String, String> getAllProperty() {
        return propertiesMap;
    }

}
