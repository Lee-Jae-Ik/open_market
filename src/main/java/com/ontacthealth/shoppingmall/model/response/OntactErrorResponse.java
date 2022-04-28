package com.ontacthealth.shoppingmall.model.response;

import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/21
 */
public class OntactErrorResponse<T> extends OntactApiResponse {
    @Setter
    private String changeMessage;

    public OntactErrorResponse(OntactApiResult result) {
        super(result, null);
    }

    public OntactErrorResponse(OntactApiResult result, T data) {
        super(result, data);
    }

    @Override
    public String getMessage() {
        if(!StringUtils.hasText(this.changeMessage)) return super.getMessage();
        else return this.changeMessage;
    }

}
