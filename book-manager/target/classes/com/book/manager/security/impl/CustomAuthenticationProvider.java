package com.book.manager.security.impl;

import com.book.manager.dao.security.UserDao;
import com.book.manager.domain.security.UserEntity;
import com.book.util.redis.JedisClient;
import com.book.util.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;


/**
 * Created by $_Mai on 2017/10/16.
 * 用户密码验证失败后处理类
 */
@Service("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDao userDao ;
    @Autowired
    private JedisClient jedisClient ;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName() ; // 用户名
        Integer timeOut = 60 * 60 * 24 ;
        String key = "MANAGER_" + userName + "_LOGIN_ERROR" ;
        String msgKey = "MANAGER_" + userName + "_MSG" ; // 用户登录异常信息
        RedisUtil.put(jedisClient,"userName",timeOut,userName);
        UserEntity userEntity = userDao.getUserByUserAccount(userName) ;
        if(null == userEntity){
            RedisUtil.put(jedisClient,msgKey,timeOut,"用户名或密码错误");
            return null ;
        }
        if(userEntity.getStatus().equals(UserEntity.StatusEnum.NO.getValue())){
            RedisUtil.put(jedisClient,msgKey,timeOut,"用户:" + userName + " 已被冻结,请联系管理员");
            return null ;
        }
        boolean flag = jedisClient.get().exists(key) ;
        if(flag){ // 用户存在密码输入错误记录
            String value = RedisUtil.get(jedisClient,key) ;
            Integer valueInt = Integer.parseInt(value) ;
            if(valueInt.equals(2)){
                RedisUtil.put(jedisClient,msgKey,timeOut,"用户:" + userName + " 已被冻结,请联系管理员");
                UserEntity updateEntity = new UserEntity() ;
                updateEntity.setId(userEntity.getId());
                updateEntity.setStatus(UserEntity.StatusEnum.NO.getValue());
                userDao.updateByPrimaryKeySelective(updateEntity) ;
                return null ;
            }else{
                RedisUtil.put(jedisClient,key,timeOut,String.valueOf(valueInt + 1));
                RedisUtil.put(jedisClient,msgKey,timeOut,"24小时内密码输入错误次数超过3次,用户将会被冻结。输入密码错误次数:" + (valueInt+1) + " 次");
                return null ;
            }
        }
        else{
            RedisUtil.put(jedisClient,key,timeOut,String.valueOf(1));
            RedisUtil.put(jedisClient,msgKey,timeOut,"24小时内密码输入错误次数超过3次,用户将会被冻结。输入密码错误次数:" + 1+" 次");
            return null ;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return  aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
