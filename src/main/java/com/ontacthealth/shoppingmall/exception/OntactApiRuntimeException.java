package com.ontacthealth.shoppingmall.exception;

import com.ontacthealth.shoppingmall.model.response.OntactApiResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/22
 */
@Slf4j
public class OntactApiRuntimeException extends RuntimeException{
    private OntactApiResult ontactApiResult;

    public OntactApiRuntimeException() {
        super();
        ontactApiResult = OntactApiResult.SERVER_ERROR;
    }

    public OntactApiRuntimeException(OntactApiResult ontactApiResult) {
        super(ontactApiResult.getMessage());
        this.ontactApiResult = ontactApiResult;
    }

    public OntactApiRuntimeException(OntactApiResult ontactApiResult, String message) {
        super(message);
        ontactApiResult.setMessage(message);
        this.ontactApiResult = ontactApiResult;
    }

    public OntactApiRuntimeException(String message) {
        this(OntactApiResult.SERVER_ERROR, message);
    }

    public OntactApiRuntimeException(String message, Throwable cause) {
        super(message, cause);
        log.error("RuntimeException : {}", message);
        ontactApiResult = OntactApiResult.SERVER_ERROR;
    }

    public OntactApiRuntimeException(Throwable cause) {
        super(cause);
        log.error("RuntimeException : {}", cause);
        ontactApiResult = OntactApiResult.SERVER_ERROR;
    }

    public OntactApiRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        log.error("RuntimeException : {}", message);
        ontactApiResult = OntactApiResult.SERVER_ERROR;
    }

    public OntactApiResult getOntactApiResult() {
        return ontactApiResult;
    }

    protected void setOntactApiResult(OntactApiResult ontactApiResult) {
        this.ontactApiResult = ontactApiResult;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
