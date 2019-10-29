package com.study.vcloud.zuul.config;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * @author Pan Weilong
 * @date 2019/10/22 22:31
 * @description: 接口.
 */
public class ExceptionHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        //这里返回false
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }
}
