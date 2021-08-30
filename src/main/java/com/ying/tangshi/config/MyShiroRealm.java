package com.ying.tangshi.config;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ying.tangshi.entity.SysPermission;
import com.ying.tangshi.entity.SysRole;
import com.ying.tangshi.entity.User;
import com.ying.tangshi.mapper.SysPermissionMapper;
import com.ying.tangshi.mapper.SysRoleMapper;
import com.ying.tangshi.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 2021/8/27 - 10:34 上午
 * Lian-Ying
 **/
public class MyShiroRealm extends AuthorizingRealm {
//    用于用户查询
    @Autowired
    private UserMapper userMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;


    @Autowired
    SysPermissionMapper sysPermissionMapper;
    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String userNumber= (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称


        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_number",userNumber);
        User user = userMapper.selectOne(wrapper);

        QueryWrapper<SysRole> roleQueryWrapper = new QueryWrapper<>();

        roleQueryWrapper.eq("user_id",user.getId());//根据用户id，获取角色
        SysRole sysRole = sysRoleMapper.selectOne(roleQueryWrapper);


        QueryWrapper<SysPermission>  permissionQueryWrapper= new QueryWrapper<>();
        permissionQueryWrapper.eq("role_id",sysRole.getId());///根据角色id获取权限
        SysPermission sysPermission = sysPermissionMapper.selectOne(permissionQueryWrapper);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(sysRole.getRoleName());
        simpleAuthorizationInfo.addStringPermission(sysPermission.getPermission());

        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String userNumber = authenticationToken.getPrincipal().toString();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_number",userNumber);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            //这里返回后会报出对应异常

            return null;
        } else {
//            这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userNumber, user.getUserPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
