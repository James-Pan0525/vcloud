package com.study.vcloud.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VcloudOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(VcloudOauthApplication.class, args);
    }

}
