package com.book.service.dao.user;

import com.book.page.PageBean;
import com.book.service.model.user.User;

import java.util.List;

public interface UserMapper {

    Long insert(User record);

    Long insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);



    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    User queryUserByUserName(String userName);

    List<User> selectByPage(PageBean pageBean);

}