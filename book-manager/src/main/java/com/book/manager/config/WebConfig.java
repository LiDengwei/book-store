package com.book.manager.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.annotation.Resource;

/** 
 * Spring MVC 配置文件.
 * @ClassName: WebConfig
 * @author
 */
@Configurable
@ComponentScan(basePackages = {"com.book.manager.*"})
@EnableWebMvc
@EnableAspectJAutoProxy
public class WebConfig extends WebMvcConfigurerAdapter {

	private static final int FIRST_ORDER = 1;
	private static final int SECOND_ORDER = 2;
	private static final int THIRD_ORDER = 3;

	@Resource
	private Validator validator;

	/**
	 * 
	 * 注册模版视图解析器.
	 * @return 模版视图解析器
	 */
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(templateEngine());
		thymeleafViewResolver.setOrder(FIRST_ORDER);
		thymeleafViewResolver.setViewNames(new String[] { "/view/*" });
		thymeleafViewResolver.setContentType("text/html; charset=UTF-8");
		return thymeleafViewResolver;
	}

	/**
	 * 
	 * 注册页面视图解析器.
	 * @return 页面视图解析器
	 */
//	@Bean
//	public InternalResourceViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setOrder(SECOND_ORDER);
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/static/");
//		viewResolver.setSuffix(".html");
//		viewResolver.setViewNames(new String[] { "jsp" });
//		return viewResolver;
//	}

	/**
	 * 
	 * 处理重定向和跳转的视图解析器.
	 * 处理redirect和forward
	 * @return url视图解析器
	 */
	@Bean
	public UrlBasedViewResolver urlViewResolver() {
		UrlBasedViewResolver urlViewResolver = new UrlBasedViewResolver();
		urlViewResolver.setViewClass(JstlView.class);
		urlViewResolver.setOrder(THIRD_ORDER);
		return urlViewResolver;
	}

	/**
	 * use thymeleaf
	 * 注入模版解析器.
	 * @return 模版解析器
	 */
	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/static/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCacheable(false);
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	/**
	 * 
	 * 注入模版引擎.
	 * @return 模版引擎
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
//		Set<IDialect> dialects = new TreeSet<IDialect>();
		//dialects.add(new SpringSecurityDialect());
//		templateEngine.setAdditionalDialects(dialects);
		return templateEngine;
	}

	/*
	 * <p>Title: addResourceHandlers</p>
	 * <p>Description: </p>
	 * @param registry
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers
	 * (org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/static/images/");
		registry.addResourceHandler("/apk/**").addResourceLocations("/static/apk/");
		registry.addResourceHandler("/javascript/**").addResourceLocations("/static/javascript/");
		registry.addResourceHandler("/stylesheet/**").addResourceLocations("/static/stylesheet/");
	}

	/*
	 * <p>Title: getValidator</p>
	 * <p>Description: </p>
	 * @return
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#getValidator()
	 */
	@Override
	public Validator getValidator() {
		return validator;
	}
	
	/**
     * 
     * 文件参数解析.
     * @return 文件参数解析对象
     */
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(20971520);
        commonsMultipartResolver.setMaxInMemorySize(4096);
        return commonsMultipartResolver;
    }

}
