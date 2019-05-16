package com.book.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 在完成了上述配置后，其实已经可以生产文档内容，但是这样的文档主要针对请求本身，而描述主要来源于函数等命名产生，
 * 对用户并不友好，我们通常需要自己增加一些说明来丰富文档内容。如下所示，我们通过@ApiOperation注解来给API增加说明、
 * 通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
 * 完成上述代码添加上，启动Spring Boot程序，访问：http://dev.book.com:18080/swagger-ui.html
 * 需要配置本地hosts 127.0.0.1 dev.book.com
 */

@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
public class Swagger2 {



	@Bean
	    public Docket createRestApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.book.web.controller"))
	                .paths(PathSelectors.any())
	                .build();
	    }


	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("公告项目  restful api文档")
	                .description("平台api接口文档")
	                .termsOfServiceUrl("http://dev.book.com:18080")
	                .contact("公告项目开发团队")
	                .version("1.0")
	                .build();
	    }
}
