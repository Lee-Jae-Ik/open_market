package com.ontacthealth.shoppingmall.exception;

import com.ontacthealth.shoppingmall.base_model.response.ShoppingApiResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/22
 */
@Slf4j
public class ShoppingApiRuntimeException extends RuntimeException{
    private ShoppingApiResult shoppingApiResult;

    public ShoppingApiRuntimeException() {
        super();
        shoppingApiResult = ShoppingApiResult.SERVER_ERROR;
    }

    public ShoppingApiRuntimeException(ShoppingApiResult shoppingApiResult) {
        super(shoppingApiResult.getMessage());
        this.shoppingApiResult = shoppingApiResult;
    }

    public ShoppingApiRuntimeException(ShoppingApiResult shoppingApiResult, String message) {
        super(message);
        shoppingApiResult.setMessage(message);
        this.shoppingApiResult = shoppingApiResult;
    }

    public ShoppingApiRuntimeException(String message) {
        this(ShoppingApiResult.SERVER_ERROR, message);
    }

    public ShoppingApiRuntimeException(String message, Throwable cause) {
        super(message, cause);
        log.error("RuntimeException : {}", message);
        shoppingApiResult = ShoppingApiResult.SERVER_ERROR;
    }

    public ShoppingApiRuntimeException(Throwable cause) {
        super(cause);
        log.error("RuntimeException : {}", cause);
        shoppingApiResult = ShoppingApiResult.SERVER_ERROR;
    }

    public ShoppingApiRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        log.error("RuntimeException : {}", message);
        shoppingApiResult = ShoppingApiResult.SERVER_ERROR;
    }

    public ShoppingApiResult getOntactApiResult() {
        return shoppingApiResult;
    }

    protected void setOntactApiResult(ShoppingApiResult shoppingApiResult) {
        this.shoppingApiResult = shoppingApiResult;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
