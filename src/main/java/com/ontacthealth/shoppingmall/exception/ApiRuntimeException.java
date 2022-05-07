package com.ontacthealth.shoppingmall.exception;

import com.ontacthealth.shoppingmall.base_model.response.ShoppingApiResult;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/28
 */
public class ApiRuntimeException extends RuntimeException{
    private ShoppingApiResult shoppingApiResult;

    public ApiRuntimeException(){
        super();
        shoppingApiResult = ShoppingApiResult.SERVER_ERROR;
    }

    public ApiRuntimeException(ShoppingApiResult shoppingApiResult){
        super(shoppingApiResult.getMessage());
        shoppingApiResult = ShoppingApiResult.SERVER_ERROR;
    }
}
