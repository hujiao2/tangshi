package com.ying.tangshi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.oracle.tools.packager.Log;
import com.ying.tangshi.entity.User;
import com.ying.tangshi.mapper.UserMapper;
import com.ying.tangshi.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ts
 * @since 2021-08-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public Map userLogin(String userNumber, String userPassword) {
        Log.info("登陆接口，请求参数:"+userNumber);
        Map<String, Object> result = new HashMap<>();
        int flag = -1;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();////条件查找
        queryWrapper.eq("userNumber", userNumber);
        User user = userMapper.selectByUserNumber(userNumber);//////根据账号查找
        Log.info("用户查询:"+user);

        if (null == user) {/////根据接收得adminAccount查找对象为空,返回0
            flag = 0;
            result.put("userInfo", "null");
        } else {
            String userPass = user.getUserPassword();///////数据库查的
            if (userPass.equals(userPassword)) {////和前端的比较
                flag = 2;//////账号密码正确
                result.put("userInfo", user);
            } else {
                flag = 1;////密码错误
                result.put("userInfo", "null");
            }
        }

        result.put("userLoginFlag", flag);
        return result;
    }


}
