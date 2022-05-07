package com.ontacthealth.shoppingmall.base_model.response;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/21
 */
public class ShoppingResponse<T> {
    private ShoppingApiResult result;
    private T data;

    protected ShoppingResponse() {
    }

    public ShoppingResponse(ShoppingApiResult result, T data) {
        this.result = result;
        this.data = data;
    }

    public int getResultCode() {
        return result.getResultCode();
    }

    public ShoppingApiResult getResult() {
        return result;
    }

    public String getMessage() {
        return result.getMessage();
    }

    public T getData() {
        return data;
    }

}