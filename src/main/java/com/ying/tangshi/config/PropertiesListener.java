package com.ying.tangshi.config;


/**
 * 2021/5/9 - 12:43 上午
 * Lian-Ying
 **/


import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 配置文件加载监听器
 * @author Zyred
 */
public class PropertiesListener implements  ApplicationListener<ApplicationStartedEvent> {

    /**
     * 配置项名称  key
     */
    private String propertyFileName;

    /**
     * 构造器
     * @param name
     */
    public PropertiesListener(String name) {
        this.propertyFileName = name;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        PropertiesListenerConfig.loadAllProperties(propertyFileName);
    }
}