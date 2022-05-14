package com.ontacthealth.shoppingmall.seller.utils;

import com.ontacthealth.shoppingmall.base_model.response.ShoppingApiResult;
import com.ontacthealth.shoppingmall.exception.ShoppingApiRuntimeException;
import com.ontacthealth.shoppingmall.seller.model.schema.Seller;

/**
 * SellerUtils
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-14
 */
public class SellerUtils {

    public static Boolean checkValidationSeller(Seller seller) {
        switch (seller.getBusinessType()) {
            case "간이":
            case "일반":
            case "법인":
                return true;
            default:
                return false;
        }
    }
}
