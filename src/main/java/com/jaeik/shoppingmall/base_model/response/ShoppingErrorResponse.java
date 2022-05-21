package com.jaeik.shoppingmall.base_model.response;

import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/21
 */
public class ShoppingErrorResponse<T> extends ShoppingApiResponse {
    @Setter
    private String changeMessage;

    public ShoppingErrorResponse(ShoppingApiResult result) {
        super(result, null);
    }

    public ShoppingErrorResponse(ShoppingApiResult result, T data) {
        super(result, data);
    }

    @Override
    public String getMessage() {
        if(!StringUtils.hasText(this.changeMessage)) return super.getMessage();
        else return this.changeMessage;
    }

}
