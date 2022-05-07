package com.ontacthealth.shoppingmall.exception;

import com.ontacthealth.shoppingmall.base_model.response.ShoppingApiResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/28
 */
@Slf4j
public class InvalidRequestParameterException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "InvalidParam";
    private static final String MESSAGE_PREFIX = DEFAULT_MESSAGE + ": ";

    private ShoppingApiResult result;

    public InvalidRequestParameterException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidRequestParameterException(String message) {
        super(MESSAGE_PREFIX + message);
    }

    public InvalidRequestParameterException(String message, Throwable cause) {
        super(MESSAGE_PREFIX + message, cause);
    }

    public InvalidRequestParameterException(Throwable cause) {
        super(cause);
    }

    public InvalidRequestParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(MESSAGE_PREFIX + message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidRequestParameterException(ShoppingApiResult result, String detailMessage) {
        super(detailMessage);
        log.error("message : {}", detailMessage);
        log.error("EscrowApiResultMessage : {}", result.getMessage());
        log.error("EscrowApiResultCode : {}", result.getResultCode());
        this.result = result;
    }

    //메시지 치환방식에 사용
    public InvalidRequestParameterException(ShoppingApiResult result, String detailMessage, String arg) {
        super(detailMessage.replace("%n", arg));
        result.setMessage(detailMessage.replace("%n", arg));
        log.error("message : {}", detailMessage.replace("%n", arg));
        log.error("EscrowApiResultMessage : {}", result.getMessage());
        log.error("EscrowApiResultCode : {}", result.getResultCode());

        this.result = result;
    }

    public InvalidRequestParameterException(ShoppingApiResult result) {
        super(MESSAGE_PREFIX + result.getMessage());
        this.result = result;
    }

    public ShoppingApiResult getEscrowApiResult() {
        return result;
    }
}
