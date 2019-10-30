package com.study.vcloud.zuul.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pan Weilong
 * @date 2019/10/30 13:44
 * @description: 不需要鉴权的url
 */
@Configuration
@ConfigurationProperties(prefix = "urls")
public class FilterUrlsConfig {

    /**
     * 不需要鉴权的url
     **/
    private List<String> anon=new ArrayList<>();

    public List<String> getAnon() {
        return anon;
    }

    public void setAnon(List<String> anon) {
        this.anon = anon;
    }
}
