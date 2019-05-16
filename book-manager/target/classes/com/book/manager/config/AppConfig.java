package com.book.manager.config;

import com.book.manager.domain.DomainConfig;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;


/**
 * 
 * 主应用配置类.
 * @ClassName: AppConfig
 * @author
 */
@Configuration
@ComponentScan(basePackages = "com.book.manager")
@PropertySource("classpath:jdbc.properties")
@PropertySource("classpath:config.properties")
@Import({ DomainConfig.class })
@ImportResource({ "classpath:application-security-context.xml" })
@EnableScheduling
public class AppConfig {

	/**
	 * MD5密码加密器.
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncode() {
		return new Md5PasswordEncoder();
	}
}
