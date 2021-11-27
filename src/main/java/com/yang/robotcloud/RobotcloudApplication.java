package com.yang.robotcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yang.robotcloud.mapper")
public class RobotcloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotcloudApplication.class, args);
    }

}
