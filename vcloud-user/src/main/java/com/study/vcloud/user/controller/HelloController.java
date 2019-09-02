package com.study.vcloud.user.controller;

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

    @GetMapping
    public String getHello(){
        return "user/hello";
    }
}
