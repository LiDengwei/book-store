package com.book.manager.controller;

import com.book.manager.api.service.RoleService;
import com.book.manager.api.service.UserEntityService;
import com.book.manager.config.AppConfig;
import com.book.manager.domain.security.UserEntity;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/** 
 * Web上下文工具集.
 * @ClassName: WebContextUtils
 * @author
 */
@Component
public class WebContextUtils implements ApplicationContextAware{
	@Resource
	private UserEntityService userEntityService;
	public static ApplicationContext applicationContext ;

	/**
	 * 
	 * 获取掌厅用户.
	 * @return 掌厅用户
	 */
	public UserEntity getUser() {
		UserEntity user = null;
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userCode", userDetails.getUsername());
			user = userEntityService.findUsers(map).get(0);
		}
		return user;
	}

	public static RoleService getBean(){
		ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
		RoleService roleService=(RoleService)context.getBean("roleService");
		return roleService;
	}

	/**
	 * spring 容器初始化完成时，将spring容器存储到 applicationContext 对象中
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		WebContextUtils.applicationContext = applicationContext ;
	}

	public static <T> T getBean(String name){
		return (T)applicationContext.getBean(name) ;
	}


}
