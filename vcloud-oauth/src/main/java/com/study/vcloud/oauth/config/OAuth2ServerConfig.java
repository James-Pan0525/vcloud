package com.study.vcloud.oauth.config;


import com.study.vcloud.oauth.bean.UserVoDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Order(Integer.MIN_VALUE)
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "vcloud";

    private static final String SCOPE = "scope";

    private static final String CLIENT_ID = "client_id";

    private static final String CLIENT_SECRET = "client_secret";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
        }
    }


    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private UserDetailsService userDetailsService;
        @Autowired
        private RedisConnectionFactory redisConnectionFactory;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            //配置客户端
            clients.inMemory().withClient(CLIENT_ID)
                    .resourceIds(DEMO_RESOURCE_ID)
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes(SCOPE)
                    .authorities("oauth2")
                    .secret(CLIENT_SECRET);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
            endpoints
                    .tokenEnhancer(tokenEnhancerChain)
                    .tokenStore(redisTokenStore())
                    .accessTokenConverter(accessTokenConverter())
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService)
                    // 2018-4-3 增加配置，允许 GET、POST 请求获取 token，即访问端点：oauth/token
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
            endpoints.reuseRefreshTokens(true);
            //oauth2登录异常处理
        }

        /**
         * tokenstore 定制化处理
         * @return TokenStore
         */
        @Bean
        public TokenStore redisTokenStore() {
            RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
            //redis key 前缀
            tokenStore.setPrefix(DEMO_RESOURCE_ID+"_");
            return tokenStore;
        }



        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security
                    .allowFormAuthenticationForClients()
                    .tokenKeyAccess("isAuthenticated()")
                    .checkTokenAccess("permitAll()");
        }

        /**
         * @Author Pan Weilong
         * @Description jwt加密秘钥
         * @Date 17:58 2019/7/10
         **/
        @Bean
        public JwtAccessTokenConverter accessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey(DEMO_RESOURCE_ID);
            return converter;
        }

        /**
         * jwt 生成token 定制化处理
         * @return TokenEnhancer
         */
        @Bean
        public TokenEnhancer tokenEnhancer() {
            return (accessToken, authentication) -> {
                UserVoDetail userDto = (UserVoDetail) authentication.getUserAuthentication().getPrincipal();
                final Map<String, Object> additionalInfo = new HashMap<>(1);
                additionalInfo.put("license", DEMO_RESOURCE_ID);
                additionalInfo.put("userId" , userDto.getUserId());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
                //设置token的过期时间30分钟
                Calendar nowTime = Calendar.getInstance();
                nowTime.add(Calendar.MINUTE, 30);
                ((DefaultOAuth2AccessToken) accessToken).setExpiration(nowTime.getTime());
                return accessToken;
            };
        }


    }
}
