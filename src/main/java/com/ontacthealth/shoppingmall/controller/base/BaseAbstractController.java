package com.ontacthealth.shoppingmall.controller.base;

import com.ontacthealth.shoppingmall.model.response.OntactApiResult;
import com.ontacthealth.shoppingmall.model.response.OntactErrorResponse;
import com.ontacthealth.shoppingmall.model.response.OntactResponse;
import com.ontacthealth.shoppingmall.model.response.OntactSuccessResponse;
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
    protected <T> ResponseEntity<OntactResponse> responseApi(T body) {
        return response(new OntactSuccessResponse(body));
    }

    protected ResponseEntity<OntactErrorResponse> responseError(OntactApiResult ontactApiResult) {
        return responseError(new OntactErrorResponse(ontactApiResult));
    }

    private <T> ResponseEntity<T> response(T body) {
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHttpHeaders()).body(body);
    }

    private <T> ResponseEntity<T> responseError(T body) {
        return ResponseEntity.status(HttpStatus.OK).headers(getCommonHttpHeaders()).body(body);
    }

    protected abstract HttpHeaders getCommonHttpHeaders();
}
