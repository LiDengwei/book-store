package com.book.web.dubbo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;

@Configuration
@Order(value=1)
@ImportResource(value={"classpath:applicationContext.xml"})
public class DubboConfig {
}
