package com.study.vcloud.zuul.filter;

/**
 * @author Pan Weilong
 * @date 2019/10/29 11:27
 * @description: 过滤器，转发时添加token
 */
/*@Component
public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FORM_BODY_WRAPPER_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    *//**
     * @Author Pan Weilong
     * @Description hearder中添加token  配置文件添加zuul:sensitive-headers: 解决hearder中token被过滤掉
     * @Date 13:41 2019/10/29
     * @Param []
     * @return java.lang.Object
     **//*
    @Override
    public Object run() throws ZuulException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        RequestContext requestContext = RequestContext.getCurrentContext();
        if (authentication != null) {
            HttpServletRequest request = requestContext.getRequest();
            //requestContext.addZuulRequestHeader("Authorization", request.getHeader("Authorization"));
        }
        return null;
    }
}*/
