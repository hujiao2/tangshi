package com.ying.tangshi.service.impl;
import java.util.Date;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oracle.tools.packager.Log;
import com.ying.tangshi.entity.SysPermission;
import com.ying.tangshi.entity.SysRole;
import com.ying.tangshi.entity.User;
import com.ying.tangshi.entity.UserEmailCheck;
import com.ying.tangshi.mapper.SysPermissionMapper;
import com.ying.tangshi.mapper.SysRoleMapper;
import com.ying.tangshi.mapper.UserEmailCheckMapper;
import com.ying.tangshi.mapper.UserMapper;
import com.ying.tangshi.service.UserEmailCheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ying.tangshi.utils.DateUtils;
import com.ying.tangshi.utils.EmailUtil;
import com.ying.tangshi.utils.EmailUtils;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ts
 * @since 2021-08-25
 */
@Service
public class UserEmailCheckServiceImpl extends ServiceImpl<UserEmailCheckMapper, UserEmailCheck> implements UserEmailCheckService {

    @Autowired
    UserEmailCheckMapper userEmailCheckMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    EmailUtils emailUtils;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysPermissionMapper sysPermissionMapper;


    @Override
    public Map userEmailCheck(String userNumber, String checkNumber,
                              String userName, String userPhone,
                              String userEmail, String userClass,
                              String userState, String userStateClass,
                              String userPassword) {
        Log.info("注册接口，请求参数:" + userNumber);
        Map<String, Object> result = new HashMap<>();
        int flag = 1;

        if (null == userNumber) {
            Log.info("学号参数为空");
            flag = 0;
            result.put("userInfo", "null");
            return result;
        }


        QueryWrapper<UserEmailCheck> wrapper = new QueryWrapper<>();
        wrapper.eq("user_number", userNumber);
        UserEmailCheck emailCheck = userEmailCheckMapper.selectOne(wrapper);

        if (emailCheck.getCheckNumber().equals(checkNumber) ) {
            Log.info("验证成功" + userNumber);
            User user = new User();
            user.setUserNumber(userNumber);
            user.setUserName(userName);
            user.setUserPassword(userPassword);
            user.setUserPhone(userPhone);

            user.setUserEmail(userEmail);
            user.setUserClass(userClass);
            user.setUserState(userState);
            user.setUserStateClass(userStateClass);
            user.setUserEmail(userEmail);
            user.setLogicDel(0);
            user.setCreateTime(new Date());
            user.setVersion(1);
            user.setUpdateTime(new Date());
            int i = userEmailCheckMapper.deleteById(emailCheck.getId());
//            积极分子	level1
//            发展对象	level2
//            预备党员	level3
//            中共党员	level4
//            党务干部	level5
//            党支部书记	level6
            int userId = Convert.toInt(generateId());////用户id

            int roleId = Convert.toInt(generateId());////角色id


            SysRole sysRole = new SysRole();
            sysRole.setId(roleId);
            sysRole.setUserId(userId);
            sysRole.setRoleName("student");


            SysPermission sysPermission = new SysPermission();
            sysPermission.setRoleId(roleId);


            if (userState.equals("积极分子")){
                sysPermission.setPermission("level1");

            }
            if (userState.equals("发展对象")){
                sysPermission.setPermission("level2");

            }
            if (userState.equals("预备党员")){
                sysPermission.setPermission("level3");

            }
            if (userState.equals("中共党员")){
                sysPermission.setPermission("level4");

            }
            if (userState.equals("党务干部")){
                sysPermission.setPermission("level5");

            }
            if (userState.equals("党支部书记")){
                sysPermission.setPermission("level6");

            }

            try {


            }catch (Exception e){

                Log.info("用户注册异常"+e);
            }
            user.setId(userId);////关联roleId
            userMapper.insert(user);
            ////顺序不可更改,roleId为外键
            sysRoleMapper.insert(sysRole);

            sysPermission.setId(Convert.toInt(generateId()));
            sysPermissionMapper.insert(sysPermission);



            flag = 2;
            result.put("userNumber", user.getUserNumber());
            result.put("userName", user.getUserName());

        } else {
            Log.info("验证失败" + userNumber);
            flag = 1;
            result.put("userNumber", userNumber);
        }
        result.put("userRegisterFlag", flag);
        return result;


    }


    @Override
    public Map userSendEmailCheck(String userNumber, String userEmail) {
        Log.info("请求验证码接口，请求参数:" + userNumber);
        Map<String, Object> result = new HashMap<>();
        int flag = 1;

        if (null == userNumber & null == userEmail) {
            Log.info("学号或邮箱参数为空");
            flag = 0;
            result.put("userNumber", "null");
            result.put("userEmail", "null");


        } else {
//            User usercheck = userMapper.selectByUserNumber(userNumber);
            QueryWrapper<User> wrapper = new QueryWrapper<>();

            wrapper.eq("user_number", userNumber);
            User user = userMapper.selectOne(wrapper);
            if (null != user) {
                Log.info("该学号已经注册" + userNumber);
                flag = 1;///////学号已经注册
                result.put("userNumber", userNumber);
                result.put("userEmail", userEmail);


            } else {

                QueryWrapper<UserEmailCheck> wrapper1 = new QueryWrapper<>();
                wrapper1.eq("user_number",userNumber);////根据账号，查询验证码
                List<UserEmailCheck> userEmailChecks = userEmailCheckMapper.selectList(wrapper1);
                if (userEmailChecks.size() >= 1) {
                    for (int i = 0; i < userEmailChecks.size(); i++) {
                        UserEmailCheck userEmailCheck = userEmailChecks.get(i);
                        userEmailCheckMapper.deleteById(userEmailCheck.getId());

                    }
                }


                double aa = Math.random() * 9000 + 1000;
                System.out.println("bbb:" + (int) Math.ceil(aa));
                Log.info("验证码：" + (int) Math.ceil(aa));
                int num = (int) Math.ceil(aa);
                String str = String.valueOf(num);
                UserEmailCheck userEmailCheck = new UserEmailCheck();
                userEmailCheck.setUserNumber(userNumber);
                userEmailCheck.setUserEmail(userEmail);
                userEmailCheck.setCheckNumber(str);
                userEmailCheck.setCreateTime(new Date());
                userEmailCheck.setUpdateTime(new Date());
                userEmailCheck.setVersion(1);
                userEmailCheck.setLogicDel(1);
                userEmailCheckMapper.insert(userEmailCheck);
                emailUtils.sendVerifyCode(userEmail, str);
                Log.info("邮箱发送成功" + userNumber);
                flag = 2;
                result.put("userNumber", userNumber);
            }

        }


        result.put("userLoginFlag", flag);
        return result;
    }
    /**
     * 生成id
     *
     * @return
     */
    private Long generateId() {
        Long time = Long.parseLong(new SimpleDateFormat("ddHHmmss").format(new Date())) * 10000;
        UUID uuid = UUID.randomUUID();
        Long lastLong = uuid.getLeastSignificantBits() % 100;
        return time + lastLong;
    }
}
