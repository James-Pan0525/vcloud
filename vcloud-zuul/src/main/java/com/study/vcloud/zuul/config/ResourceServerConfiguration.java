package com.study.vcloud.zuul.config;

import com.study.vcloud.zuul.exception.AuthExceptionEntryPoint;
import com.study.vcloud.zuul.handle.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

/**
 * @author Pan Weilong
 * @date 2019/8/15 22:45
 * @description: 接口.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;
    @Autowired
    private RemoteTokenService remoteTokenService;
    @Autowired
    private FilterUrlsConfig filterUrlsConfig;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
        //经过oauth服务的连接全部放行,不然token会获取不到
        registry.antMatchers("/oauth/**").permitAll();
        for(String url:filterUrlsConfig.getAnon()){
            //公用url放行
            registry.antMatchers(url).permitAll();
        }
        //权限控制
        registry.anyRequest().access("@permissionService.hasPermission(request,authentication)");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.expressionHandler(expressionHandler);
        resources.resourceId("vcloud");
        resources.tokenServices(remoteTokenService);
        //token异常
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
        //授权异常处理
        resources.accessDeniedHandler(new CustomAccessDeniedHandler());
    }

    /**
     * 配置解决 spring-security-oauth问题
     * https://github.com/spring-projects/spring-security-oauth/issues/730
     *
     * @param applicationContext ApplicationContext
     * @return OAuth2WebSecurityExpressionHandler
     */
    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }


}
