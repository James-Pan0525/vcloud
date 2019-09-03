package com.study.vcloud.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//接口扫描
@MapperScan(basePackages = {"com.study.vcloud.user.mapper"})
public class VcloudUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(VcloudUserApplication.class, args);
    }

}
