package com.book.manager.domain;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/** 
 * 主应用配置类..
 * @ClassName: AppConfig
 * @author
 */
@Configuration
public class DomainConfig {

	/**
	 * 
	 * 注入消息源.
	 * @return 消息源
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messageResource_zh_CN");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

//	/**
//	 *
//	 * 注入校验器.
//	 * @return 校验器
//	 */
//	@Bean
//	public Validator validator() {
//		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//		validator.setValidationMessageSource(messageSource());
//		//validator.setProviderClass(org.hibernate.validator.HibernateValidator.class);
//		return validator;
//	}
}
