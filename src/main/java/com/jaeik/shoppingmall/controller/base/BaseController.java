package com.jaeik.shoppingmall.controller.base;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/21
 */
public class BaseController extends BaseAbstractController{
    @Override
    public HttpHeaders getCommonHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        Charset utf8 = StandardCharsets.UTF_8;
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, utf8));
        return httpHeaders;
    }
}
