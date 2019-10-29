package com.study.vcloud.zuul.config;

import com.sun.jersey.core.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;


@Component
@Primary
public class RemoteTokenService extends RemoteTokenServices {

    private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;
    @Autowired
    private ResourceServerProperties resourceServerProperties;

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap();
        formData.add("token", accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", this.getAuthorizationHeader(oAuth2ClientProperties.getClientId(), oAuth2ClientProperties.getClientSecret()));
        headers.set("Accept","application/json");
        Map<String, Object> map = this.postForMap(resourceServerProperties.getTokenInfoUri(), formData, headers);
        if (map.containsKey("error")) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("check_token returned error: " + map.get("error"));
            }
            throw new InvalidTokenException(accessToken);
        } else if (map.containsKey("active") && !"true".equals(String.valueOf(map.get("active")))) {
            this.logger.debug("check_token returned active attribute: " + map.get("active"));
            throw new InvalidTokenException(accessToken);
        } else {
            return tokenConverter.extractAuthentication(map);
        }
    }

    private String getAuthorizationHeader(String clientId, String clientSecret) {
        if (clientId == null || clientSecret == null) {
            this.logger.warn("Null Client ID or Client Secret detected. Endpoint that requires authentication will reject request with 401 error.");
        }

        String creds = String.format("%s:%s", clientId, clientSecret);

        try {
            return "Basic " + new String(Base64.encode(creds.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException var5) {
            throw new IllegalStateException("Could not convert String");
        }
    }

    private Map<String, Object> postForMap(String path, MultiValueMap<String, String> formData, HttpHeaders headers) {
        if (headers.getContentType() == null) {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }

        Map body = restTemplate.exchange(path, HttpMethod.POST, new HttpEntity(formData, headers), Map.class, new Object[0]).getBody();
        return body;
    }
}
