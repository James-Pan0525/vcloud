package com.study.vcloud.oauth.controller;

import com.study.vcloud.oauth.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pan Weilong
 * @date 2019/9/2 16:21
 * @description: 接口.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping
    public String getHello(){
        return "oauth/hello";
    }

    @GetMapping("/getFeignHello")
    public String getFeignHello(){
        return helloService.getHello();
    }
}
