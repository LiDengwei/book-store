package com.book.service.api.user;

import com.book.common.ResultMsg;
import com.book.exceptions.APIException;
import com.book.service.model.user.User;


public interface UserServiceApi {

    /**
     * 添加
     * @param record
     * @return
     */
    Long insert(User record);

    /**
     * 非空添加
     * @param record
     * @return
     */
    Long insertSelective(User record);

    /**
     * 主键查询
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);

    /**
     * 非空更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 用户登录方法
     * @return
     */
    ResultMsg login(String phoneCode, String username, String password);

    /**
     * 忘记密码与修改登录密码
     * @param phoneCode
     * @param phoneNumber
     * @param verifyCode
     * @param oldPassword
     * @param newPassword
     * @param reNewPassword
     * @return
     */
    ResultMsg updatePassword(String phoneCode, String phoneNumber, String verifyCode,
                             String oldPassword, String newPassword, String reNewPassword, User user) throws APIException ;

    /**
     * 踢用户下线
     * @param id
     * @return
     */
    Boolean kickOutUser(Long id);


}
