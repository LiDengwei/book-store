package com.book.manager.security;

import com.book.manager.domain.security.UserEntity;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;


/**
 * 
 * TODO(这里用一句话描述这个类的职责).
 * @ClassName: MyAccessDecisionManager
 * @author Johnny_L_Q
 */
@Component("myAccessDecisionManager")
public class MyAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (authentication == null || !authentication.isAuthenticated())
			throw new InsufficientAuthenticationException("Not Authenticated");
		if (configAttributes == null || configAttributes.size() == 0) {
			return;
		}
		boolean role = false;
		for (ConfigAttribute configAttribute : configAttributes) {
			if (configAttribute instanceof SecurityConfig) {
				if (authentication.getPrincipal() instanceof String) {
					if (authentication.getPrincipal().toString()
							.equals("anonymousUser")) {
						throw new AccessDeniedException("你没有访问该模块的权限！");
					}
				} else if (authentication.getPrincipal() instanceof UserEntity) {
					role = false;
				}
			}
		}
		if (role) {
			throw new AccessDeniedException("Role check fails.");
		}
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
