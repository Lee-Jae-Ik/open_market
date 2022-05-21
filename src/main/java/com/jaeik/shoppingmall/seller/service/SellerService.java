package com.jaeik.shoppingmall.seller.service;

import com.jaeik.shoppingmall.seller.model.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * SellerService
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-14
 */
public interface SellerService {
    SellerDto saveSeller(SellerSignUpDto sellerSignUpDto);

    List<SellerListDto> showSellerList(Pageable pageable);
    SellerDto showSellerDetail(Long sellerId);

    SellerIdDto submitSeller(Long sellerId);
    SellerSubmitFailDto notSubmitSeller(Long sellerId,String message);
}
