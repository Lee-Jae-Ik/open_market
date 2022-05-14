package com.ontacthealth.shoppingmall.seller.service.impl;

import com.ontacthealth.shoppingmall.base_model.response.ShoppingApiResult;
import com.ontacthealth.shoppingmall.exception.ShoppingApiRuntimeException;
import com.ontacthealth.shoppingmall.seller.model.schema.Seller;
import com.ontacthealth.shoppingmall.seller.repository.SellerRepository;
import com.ontacthealth.shoppingmall.seller.service.SellerService;
import com.ontacthealth.shoppingmall.seller.utils.SellerUtils;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * SellerServiceImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-14
 */
@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    @Transactional(rollbackFor =
            {NullPointerException.class, IllegalAccessException.class})
    public Seller saveSeller(Seller seller) {
        Optional<Seller> findSeller = sellerRepository.findById(seller.getId());
        if (findSeller.isPresent()){
            throw new ShoppingApiRuntimeException(ShoppingApiResult.DUPLICATION_SELLER_ID);
        }

        if (!SellerUtils.checkValidationSeller(seller)) {
            throw new ShoppingApiRuntimeException(ShoppingApiResult.WRONG_BUSINESS_TYPE);
        }

        return sellerRepository.save(seller);
    }
}
