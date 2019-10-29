package com.study.vcloud.common.token;

import com.study.vcloud.common.bean.UserVO;
import com.study.vcloud.common.utils.UserUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pan Weilong
 * @date 2019/10/29 10:07
 * @description: 接口.
 */
@Configuration
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(UserVO.class);
    }

    /**
     * @Author Pan Weilong
     * @Description 解析token
     * @Date 13:39 2019/10/29
     * @Param [parameter, mavContainer, nativeWebRequest, binderFactory]
     * @return java.lang.Object
     **/
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory binderFactory) throws Exception {
        UserVO userVO = new UserVO();
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        Integer userId=UserUtils.getUserId(request);
        userVO.setUserId(userId);
        return userVO;
    }
}
