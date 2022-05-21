package com.jaeik.shoppingmall.base_model.response;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/21
 */
public class ShoppingApiResponse<T> {
    private ShoppingApiResult result;
    private T data;

    protected ShoppingApiResponse() {
    }

    public ShoppingApiResponse(ShoppingApiResult result, T data) {
        this.result = result;
        this.data = data;
    }

    public ShoppingApiResponse(ShoppingApiResult result, T data, String message) {
        this.result = result;
        this.data = data;
        this.result.setMessage(message);
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
