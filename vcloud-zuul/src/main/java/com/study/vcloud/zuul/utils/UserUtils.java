package com.study.vcloud.zuul.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pan Weilong
 * @date 2019/9/9 16:49
 * @description: 接口.
 */
public class UserUtils {

    /**
     * token请求头名称
     */
    private static String REQ_HEADER = "Authorization";

    /**
     * token分割符
     */
    private static String TOKEN_SPLIT = "bearer ";

    public static Integer getUserId(String str) {
        JSONObject json = JSONObject.parseObject(str);
        if (json.containsKey("userId")) {
            return  json.getInteger("userId");
        }
        return -1;
    }

    /**
     * 获取请求中token
     *
     * @param httpServletRequest request
     * @return token
     */
    public static String getToken(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader(REQ_HEADER);
        return StringUtils.substringAfter(authorization,TOKEN_SPLIT);
    }
}
