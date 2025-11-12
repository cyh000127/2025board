package com.study.mvc.myboard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(basePackages = "org.scoula.mapper")
public class MyBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBoardApplication.class, args);
    }

}
