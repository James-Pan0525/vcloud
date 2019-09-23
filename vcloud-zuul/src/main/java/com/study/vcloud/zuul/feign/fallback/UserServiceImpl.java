package com.study.vcloud.zuul.feign.fallback;

import com.study.vcloud.zuul.feign.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Pan Weilong
 * @date 2019/9/3 16:32
 * @description: 接口.
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public Boolean hasAuth(String url, Long userId, String method) {
        return null;
    }
}
