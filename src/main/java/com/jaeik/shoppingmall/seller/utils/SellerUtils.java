package com.jaeik.shoppingmall.seller.utils;

/**
 * SellerUtils
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-14
 */
public class SellerUtils {

    public static Boolean checkValidationSeller(String businessType) {
        switch (businessType) {
            case "간이":
            case "일반":
            case "법인":
                return true;
            default:
                return false;
        }
    }
}
