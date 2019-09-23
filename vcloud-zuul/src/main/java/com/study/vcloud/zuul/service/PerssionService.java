package com.study.vcloud.zuul.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pan Weilong
 * @date 2019/8/15 23:02
 * @description: 接口.
 */
public interface PerssionService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
