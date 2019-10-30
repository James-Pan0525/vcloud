package com.study.vcloud.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * @author Pan Weilong
 * @date 2019/10/29 15:07
 * @description: 接口.
 */
public class UserUtils {


    /**
     * 获取用户id
     **/
    public static Integer getUserId(HttpServletRequest request) {
        String token=getToken(request);
        String key = Base64.getEncoder().encodeToString("vcloud".getBytes());
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.get("userId").toString());
    }

    /**
     * 获取token
     **/
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return StringUtils.substringAfter(token, "bearer ");
    }


}
