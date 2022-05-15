package com.ontacthealth.shoppingmall.seller.repository.custom;

import com.ontacthealth.shoppingmall.seller.model.schema.Seller;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * SellerRepositoryCustom
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/05/14
 */
public interface SellerRepositoryCustom {
    List<Seller> showSellerListNonSubmit(Pageable pageable);
    void sellerUpdateMessage(Long sellerId,String message);
}
