package com.ontacthealth.shoppingmall.seller.service;

import com.ontacthealth.shoppingmall.seller.model.dto.SellerIdDto;
import com.ontacthealth.shoppingmall.seller.model.dto.SellerListDto;
import com.ontacthealth.shoppingmall.seller.model.schema.Seller;
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
    Seller saveSeller(Seller seller);
    List<SellerListDto> showSellerList(Pageable pageable);
    Seller showSellerDetail(Long sellerId);
    SellerIdDto submitSeller(Long sellerId);
}
