package com.ontacthealth.shoppingmall.controller.base;

import com.ontacthealth.shoppingmall.base_model.response.ShoppingApiResult;
import com.ontacthealth.shoppingmall.base_model.response.ShoppingErrorResponse;
import com.ontacthealth.shoppingmall.base_model.response.ShoppingResponse;
import com.ontacthealth.shoppingmall.base_model.response.ShoppingSuccessResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/21
 */
public abstract class BaseAbstractController {
    protected <T> ResponseEntity<ShoppingResponse> responseApi(T body) {
        return response(new ShoppingSuccessResponse(body));
    }

    protected ResponseEntity<ShoppingErrorResponse> responseError(ShoppingApiResult shoppingApiResult) {
        return responseError(new ShoppingErrorResponse(shoppingApiResult));
    }

    private <T> ResponseEntity<T> response(T body) {
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHttpHeaders()).body(body);
    }

    private <T> ResponseEntity<T> responseError(T body) {
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHttpHeaders()).body(body);
    }

    protected abstract HttpHeaders getCommonHttpHeaders();
}
