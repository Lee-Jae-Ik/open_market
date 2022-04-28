package com.ontacthealth.shoppingmall.model.response;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/21
 */
public class OntactSuccessResponse<T> extends OntactResponse {
    public OntactSuccessResponse(T data) {
        super(OntactApiResult.SUCCESS, data);
    }
}
