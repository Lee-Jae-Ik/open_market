package com.jaeik.shoppingmall.base_model.response;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daehoonchoi
 * @version 1.0
 * @see
 * @since 2021/12/21
 */
public enum ShoppingApiResult {
    NONE(0, "NONE"),
    /* Success Area */
    SUCCESS(200, "success"),
    /* API Common Error Area */
    SERVER_ERROR(-1, "서버 오류. 온택트헬스에 문의하세요."),
    INTERNAL_SERVER_ERROR(5000, "알고리즘 API 서버 오류"),
    NO_DATA (9999,"조회한 데이터가 존재하지 않습니다."),
    NOT_INSERT_STOCK(9001, "재고는 최소 1개 이상 입력 해주셔야 합니다."),
    NOT_INSERT_PRICE(9002, "가격은 최소 100원 이상 입력 해주셔야 합니다."),
    WRONG_CATEGORY_ID(9003, "유효하지 않은 카테고리 입니다."),
    DUPLICATION_SELLER_ID(9004, "이미 가입된 아이디 입니다."),
    WRONG_BUSINESS_TYPE(9005, "잘못된 사업자 유형 입니다.")
    ;

    @Getter
    private final int resultCode;

    @Getter
    @Setter
    private String message;

    @Getter
    private String defaultMessage;

    ShoppingApiResult(int resultCode){
        this.resultCode = resultCode;
        this.message = searchByResultCode(resultCode).getMessage();
    }

    ShoppingApiResult(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public void initMessage() {
        this.message = this.defaultMessage;
    }

    public static final Map<Integer, ShoppingApiResult> OntactApiResultMap = new HashMap<>();
    static {
        for(ShoppingApiResult shoppingApiResult : ShoppingApiResult.values()){
            OntactApiResultMap.put(shoppingApiResult.getResultCode(), shoppingApiResult);
        }
    }

    public static ShoppingApiResult searchByResultCode(int resultCode){
        return OntactApiResultMap.get(resultCode);
    }
}
