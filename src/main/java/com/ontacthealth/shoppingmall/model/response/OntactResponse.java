package com.ontacthealth.shoppingmall.model.response;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/21
 */
public class OntactResponse<T> {
    private OntactApiResult result;
    private T data;

    protected OntactResponse() {
    }

    public OntactResponse(OntactApiResult result, T data) {
        this.result = result;
        this.data = data;
    }

    public int getResultCode() {
        return result.getResultCode();
    }

    public OntactApiResult getResult() {
        return result;
    }

    public String getMessage() {
        return result.getMessage();
    }

    public T getData() {
        return data;
    }

}