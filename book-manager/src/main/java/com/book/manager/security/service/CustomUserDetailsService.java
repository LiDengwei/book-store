package com.book.manager.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

/** 
 * TODO(这里用一句话描述这个类的职责).
 * 要实现使用数据库管理用户，需要自定义用户登录功能，而Spring已经为我们提供了接口UserDetailsService
 * @ClassName: CustomUserDetailsService
 * @author Johnny_L_Q
 */
public interface CustomUserDetailsService extends UserDetailsService{

    
}
