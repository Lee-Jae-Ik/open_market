package com.ontacthealth.shoppingmall.base_model.response;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/21
 */
public class ShoppingSuccessResponse<T> extends ShoppingResponse {
    public ShoppingSuccessResponse(T data) {
        super(ShoppingApiResult.SUCCESS, data);
    }
}
