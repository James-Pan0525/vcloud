package com.study.vcloud.oauth.feign;

import com.study.vcloud.oauth.bean.UserVo;
import com.study.vcloud.oauth.feign.fallback.UserServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Pan Weilong
 * @date 2019/9/3 16:26
 * @description: 接口.
 */
@FeignClient(value = "vcloud-user-server",fallback = UserServiceImpl.class)
public interface UserService {

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    @GetMapping("/sysUser/getUserByUsername/{username}")
    UserVo getUserByUsername(@PathVariable("username") String username);
}
