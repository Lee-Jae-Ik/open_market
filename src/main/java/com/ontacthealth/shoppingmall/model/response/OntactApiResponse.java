package com.ontacthealth.shoppingmall.model.response;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/21
 */
public class OntactApiResponse<T> {
    private OntactApiResult result;
    private T data;

    protected OntactApiResponse() {
    }

    public OntactApiResponse(OntactApiResult result, T data) {
        this.result = result;
        this.data = data;
    }

    public OntactApiResponse(OntactApiResult result, T data, String message) {
        this.result = result;
        this.data = data;
        this.result.setMessage(message);
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
