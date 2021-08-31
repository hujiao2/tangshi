package com.ying.tangshi.controller;


import com.ying.tangshi.entity.UserEmailCheck;
import com.ying.tangshi.service.UserEmailCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import com.ying.tangshi.controller.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ts
 * @since 2021-08-25
 */
@RestController
@RequestMapping("userEmail")
public class UserEmailCheckController extends BaseController {
    @Autowired
    UserEmailCheckService userEmailCheckService;



    @RequestMapping("/userRegisterEmailCheck")
    public Object userRegisterEmailCheck(@RequestParam("userNumber") String userNumber,
                                         @RequestParam("userName") String userName,
                                         @RequestParam("userPhone") String userPhone,
                                         @RequestParam("userEmail") String userEmail,
                                         @RequestParam("userClass") String userClass,
                                         @RequestParam("userState") String userState,
                                         @RequestParam("userStateClass") String userStateClass,
                                         @RequestParam("userPassword") String userPassword,
                                         @RequestParam("userEmailCheck") String UserEmailCheck) {
        return userEmailCheckService.userEmailCheck(userNumber,UserEmailCheck, userName, userPhone, userEmail, userClass, userState, userStateClass, userPassword);
    }


    @RequestMapping("/userSendEmailCheck")
    public Object userSendEmailCheck(@RequestParam("userNumber") String userNumber,
                                         @RequestParam("userEmail") String userEmail) {
        return userEmailCheckService.userSendEmailCheck(userNumber,userEmail);
    }

    @RequestMapping("/userSendEmailCheckRetrieve")
    public Object userSendEmailCheckRetrieve(@RequestParam("userEmail") String userEmail) {
        return userEmailCheckService.userSendEmailCheckRetrieve(userEmail);
    }

    @RequestMapping("/userEmailRetrieveCheck")
    public Object userEmailRetrieveCheck(@RequestParam("userEmail") String userEmail,@RequestParam("userEmailCheck") String userEmailCheck,@RequestParam("userPassword") String userPassword) {
        return userEmailCheckService.userEmailRetrieveCheck(userEmail,userEmailCheck,userPassword);
    }


}

