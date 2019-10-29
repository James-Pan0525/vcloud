package com.study.vcloud.user.controller;

import com.study.vcloud.common.bean.UserVO;
import com.study.vcloud.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private SysUserService sysUserService;

    @GetMapping
    public String getHello(UserVO userVO){
        System.out.println("用户id:"+userVO.getUserId());
        return "user/hello";
    }

    @GetMapping("/getUserById/{id}")
    public String getUser(@PathVariable Integer id){
        return sysUserService.selectById(id).toString();
    }
}
