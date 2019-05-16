package com.book.manager.security.impl;

import com.book.manager.dao.security.AuthorityDao;
import com.book.manager.dao.security.UserDao;
import com.book.manager.domain.security.AuthorityEntity;
import com.book.manager.domain.security.UserEntity;
import com.book.manager.security.service.CustomUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 该类的主要作用是为Spring Security提供一个经过用户认证后的UserDetails。
 * 该UserDetails包括用户名、密码、是否可用、是否过期等信息。
 * TODO(这里用一句话描述这个类的职责).
 * @ClassName: CustomUserDetailsServiceImpl
 * @author Johnny_L_Q
 */
@Service("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    /**
     * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）.
     */
    private static final long serialVersionUID = 1L;
    
    @Resource
    private UserDao userDao;
    @Resource
    private AuthorityDao authorityDao;


    /*
     * 根据用户名判断是否存在
     * <p>Title: loadUserByUsername</p>
     * <p>Description: </p>
     * @param arg0
     * @return
     * @throws UsernameNotFoundException
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserEntity user = userDao.getUserByUserAccount(username);

        if (null == user) {
            throw new UsernameNotFoundException("用户" + username + "不存在");
        }
        if (UserEntity.StatusEnum.NO.getValue()==user.getStatus()){
            throw new UsernameNotFoundException("用户" + username + "已禁用");
        }

        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        //得到用户的权限
        List<AuthorityEntity> list = authorityDao.getAuthoritiesByUserId(username);
        for (int i = 0; i < list.size(); i++) {
            auths.add(new GrantedAuthorityImpl(list.get(i).getAuthorityName()));
            System.out.println("loadUserByUsername : " + list.get(i));
        }
      //因为UserEntity实现了UserDetails，所以也可以直接返回user
        return new User(username, user.getPassword(), true, true, true, true, auths);
    }

}
