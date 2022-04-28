package com.ontacthealth.shoppingmall.exception;

import com.ontacthealth.shoppingmall.model.response.OntactApiResult;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/28
 */
public class ApiRuntimeException extends RuntimeException{
    private OntactApiResult ontactApiResult;

    public ApiRuntimeException(){
        super();
        ontactApiResult = OntactApiResult.SERVER_ERROR;
    }

    public ApiRuntimeException(OntactApiResult ontactApiResult){
        super(ontactApiResult.getMessage());
        ontactApiResult = OntactApiResult.SERVER_ERROR;
    }
}
