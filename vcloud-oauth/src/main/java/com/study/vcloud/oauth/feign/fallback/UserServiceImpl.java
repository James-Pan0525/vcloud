package com.study.vcloud.oauth.feign.fallback;

import com.study.vcloud.oauth.bean.UserVo;
import com.study.vcloud.oauth.feign.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Pan Weilong
 * @date 2019/9/3 16:32
 * @description: 接口.
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserVo getUserByUsername(String username) {
        return null;
    }
}
