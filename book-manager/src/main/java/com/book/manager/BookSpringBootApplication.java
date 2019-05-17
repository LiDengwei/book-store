package com.book.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by henry on 2019/5/17 17:21
 */
@SpringBootApplication//这里的MybatisAutoConfiguration.class对应你的数据库框架
public class BookSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookSpringBootApplication.class, args);
    }
}
