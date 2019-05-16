package com.book.service.impl.user;


import com.book.common.ResultMsg;
import com.book.exceptions.APIException;
import com.book.service.api.user.*;
import com.book.service.dao.user.UserMapper;
import com.book.service.model.user.*;
import com.book.util.redis.JedisClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户
 */
@Service("userServiceApi")
public class UserServiceApiImpl implements UserServiceApi {

    private static org.slf4j.Logger loginLogger = LoggerFactory.getLogger("loginLogger");
    private static org.slf4j.Logger registerLogger = LoggerFactory.getLogger("registerLogger");


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;


    private Object lock = new Object();
    /**
     * 添加
     * @param user
     * @return
     */
    @Override
    public Long insert(User user) {
        userMapper.insert(user);
        return user.getId();
    }

    /**
     * 添加
     * @param user
     * @return
     */
    @Override
    public Long insertSelective(User user) {
        userMapper.insert(user);
        return user.getId();
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据ID修改实体非空的属性
     * @param user
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(User user) {
        synchronized (lock){
            return userMapper.updateByPrimaryKeySelective(user);
        }
    }

    @Override
    public ResultMsg login(String phoneCode, String username, String password) {
        return null;
    }

    @Override
    public ResultMsg updatePassword(String phoneCode, String phoneNumber, String verifyCode, String oldPassword, String newPassword, String reNewPassword, User user) throws APIException {
        return null;
    }

    @Override
    public Boolean kickOutUser(Long id) {
        return null;
    }
}


