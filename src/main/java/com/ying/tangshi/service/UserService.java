package com.ying.tangshi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ying.tangshi.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ts
 * @since 2021-08-25
 */
public interface UserService extends IService<User> {
    Map userLogin(String userAccount, String userPassword);


}
