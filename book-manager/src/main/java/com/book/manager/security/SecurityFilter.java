package com.book.manager.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

/**
 * 
 * TODO(这里用一句话描述这个类的职责).
 * @ClassName: SecurityFilter
 * @author Johnny_L_Q
 */
public class SecurityFilter extends AbstractSecurityInterceptor implements
		Filter {

	@Resource
	@Qualifier("securityResourceService")
	private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

	@Resource
	@Qualifier("authenticationManager")
	@Override
	public void setAuthenticationManager(AuthenticationManager newManager) {
		super.setAuthenticationManager(newManager);
	}

	@Resource
	@Qualifier("myAccessDecisionManager")
	@Override
	public void setAccessDecisionManager(
			AccessDecisionManager accessDecisionManager) {
		super.setAccessDecisionManager(accessDecisionManager);
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		FilterInvocation invocation = new FilterInvocation(servletRequest,
				servletResponse, filterChain);
		InterceptorStatusToken interceptorStatusToken = super
				.beforeInvocation(invocation);
		try {
			invocation.getChain().doFilter(servletRequest, servletResponse);
		} finally {
			super.afterInvocation(interceptorStatusToken, null);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.filterInvocationSecurityMetadataSource;
	}

}
