package com.study.vcloud.oauth.service;

import com.study.vcloud.oauth.bean.UserVoDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Pan Weilong
 * @date 2019/7/9 15:57
 * @description: 接口.
 */

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVoDetail userVoDetail = new UserVoDetail();
        userVoDetail.setUserId(1L);
        userVoDetail.setUsername("hello");
        userVoDetail.setPassword("hello");
        return userVoDetail;
    }
}

