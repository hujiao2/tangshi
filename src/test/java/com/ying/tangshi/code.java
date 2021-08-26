package com.ying.tangshi;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * 2020/7/20 - 10:43 上午
 * Lian-Ying
 **/
public class code {
    public static void main(String[] args) {
        getCode();

    }

    public static void getCode() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        String path = "/Users/lianying/Desktop/毕业设计/古诗背诵小程序/mybatis";
        gc.setOutputDir(path);
        gc.setAuthor("ying");
        gc.setOpen(false);
        gc.setFileOverride(false);////是否覆盖
        gc.setServiceName("%sService");///去service前缀
        gc.setIdType(IdType.AUTO);
        gc.setDateType(DateType.ONLY_DATE);


        gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/tangshiSystem?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=true&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("sa123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("blog");
        pc.setParent("com.ying.tangshi");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //resume_date_user
//        strategy.setInclude(new String[] { "sys_admin,sys_doctor,sys_doctor_learn,sys_illnessinfo,sys_login,sys_message,sys_user" });
        strategy.setInclude("sys_admin","sys_tang","sys_tang_voice","sys_user");
//        strategy.setInclude("sys_see_doctor");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);


        ////自动填充
        strategy.setLogicDeleteFieldName("deleted");
        TableFill gmtCreate = new TableFill("create_time", FieldFill.INSERT);
        TableFill gmtUpdate = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtUpdate);

        strategy.setTableFillList(tableFills);

        //乐观锁
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);////驼峰
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);

        mpg.execute();
    }
}
