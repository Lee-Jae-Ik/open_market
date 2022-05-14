package com.ontacthealth.shoppingmall.seller.service.impl;

import com.ontacthealth.shoppingmall.base_model.response.ShoppingApiResult;
import com.ontacthealth.shoppingmall.exception.ShoppingApiRuntimeException;
import com.ontacthealth.shoppingmall.seller.model.dto.SellerIdDto;
import com.ontacthealth.shoppingmall.seller.model.dto.SellerListDto;
import com.ontacthealth.shoppingmall.seller.model.schema.Seller;
import com.ontacthealth.shoppingmall.seller.repository.SellerRepository;
import com.ontacthealth.shoppingmall.seller.service.SellerService;
import com.ontacthealth.shoppingmall.seller.utils.SellerUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SellerServiceImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-14
 */
@Service
@Transactional(readOnly = true)
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    @Transactional(rollbackFor = {
            NullPointerException.class,
            IllegalAccessException.class
    })
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

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Caching(
            evict = @CacheEvict(
                    key = "#sellerListDto.id + '.' + #sellerListDto.companyName + '.' + #sellerListDto.createdDate",
                    value = "SELLER"
            )
    )
    public List<SellerListDto> showSellerList(Pageable pageable) {
        List<Seller> sellerList = sellerRepository.showSellerListNonSubmit(pageable);
        return sellerList.stream()
                .map(seller ->
                        SellerListDto.builder()
                                .id(seller.getId())
                                .companyName(seller.getCompanyName())
                                .createdDate(seller.getCreatedDate()).build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Seller showSellerDetail(Long sellerId) {
        return Optional.ofNullable(sellerRepository.findSellerBySellerId(sellerId))
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.NO_DATA, "해당 셀러는 삭제된 셀러이거나 존재하지 않은 셀러 입니다."));
    }

    @Override
    @Transactional(rollbackFor = {
            NullPointerException.class,
            IllegalAccessException.class
    })
    public SellerIdDto submitSeller(Long sellerId) {
        Seller findSeller = Optional.ofNullable(sellerRepository.findSellerBySellerId(sellerId))
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.NO_DATA, "해당 셀러는 삭제된 셀러이거나 존재하지 않은 셀러 입니다."));

        findSeller.setAcceptCheck(true);
        sellerRepository.save(findSeller);
        return SellerIdDto.builder().id(findSeller.getId()).acceptCheck(findSeller.getAcceptCheck()).build();
    }
}
