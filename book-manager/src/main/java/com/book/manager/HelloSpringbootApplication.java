package com.book.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//这里的MybatisAutoConfiguration.class对应你的数据库框架
public class BookSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSpringbootApplication.class, args);
	}
}
