package com.ying.tangshi.service;

import com.ying.tangshi.entity.UserEmailCheck;
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
public interface UserEmailCheckService extends IService<UserEmailCheck> {
    Map userEmailCheck(String userNumber, String checkNumber, String userName, String userPhone, String userEmail, String userClass, String userState, String userStateClass, String userPassword);

    Map userSendEmailCheck(String userNumber, String userEmail);
}
