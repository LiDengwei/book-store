package com.book.manager.security;

import com.book.manager.api.service.ResourceService;
import com.book.manager.domain.security.ResourceEntity;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * TODO(这里用一句话描述这个类的职责).
 * @ClassName: SecurityResourceService
 * @author Johnny_L_Q
 */
@Component("securityResourceService")
public class SecurityResourceService implements
		FilterInvocationSecurityMetadataSource {

	@Resource
	private ResourceService resourceService;

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();
		for (ResourceEntity resource : this.resourceService.getAllResource()) {
			configAttributes.add(new SecurityConfig(resource.getResourceUrl()));
		}
		return configAttributes;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		for (ConfigAttribute configAttribute : this.getAllConfigAttributes()) {
			if (url.equals(configAttribute.getAttribute())) {
				Set<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();
				configAttributes.add(configAttribute);
				return configAttributes;
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
