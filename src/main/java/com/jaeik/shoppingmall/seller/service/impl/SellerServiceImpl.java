package com.jaeik.shoppingmall.seller.service.impl;

import com.jaeik.shoppingmall.base_model.response.ShoppingApiResult;
import com.jaeik.shoppingmall.exception.ShoppingApiRuntimeException;
import com.jaeik.shoppingmall.seller.model.dto.*;
import com.jaeik.shoppingmall.seller.model.schema.Seller;
import com.jaeik.shoppingmall.seller.repository.SellerRepository;
import com.jaeik.shoppingmall.seller.service.SellerService;
import com.jaeik.shoppingmall.seller.utils.SellerUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@Transactional(rollbackFor = Exception.class)
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    @CacheEvict("sellerCache")
    public SellerDto saveSeller(SellerSignUpDto sellerSignUpDto) {

        Optional<Seller> findSeller = Optional.ofNullable(sellerRepository.findSellerByBusinessNumber(sellerSignUpDto.getBusinessNumber()));
        if (findSeller.isPresent()) {
            throw new ShoppingApiRuntimeException(ShoppingApiResult.DUPLICATION_SELLER_ID);
        }

        if (Boolean.FALSE.equals(SellerUtils.checkValidationSeller(sellerSignUpDto.getBusinessType()))) {
            throw new ShoppingApiRuntimeException(ShoppingApiResult.WRONG_BUSINESS_TYPE);
        }

        Seller insertSeller =  sellerRepository.save(Seller.builder()
                .businessNumber(sellerSignUpDto.getBusinessNumber())
                .businessAddress(sellerSignUpDto.getBusinessAddress())
                .businessCEOName(sellerSignUpDto.getBusinessCEOName())
                .businessCallNumber(sellerSignUpDto.getBusinessCallNumber())
                .businessType(sellerSignUpDto.getBusinessType())
                .companyName(sellerSignUpDto.getCompanyName())
                .acceptCheck(false)
                .createdDate(LocalDateTime.now())
                .build());

        return SellerDto.builder()
                .id(insertSeller.getId())
                .businessCEOName(insertSeller.getBusinessCEOName())
                .companyName(insertSeller.getCompanyName())
                .businessType(insertSeller.getBusinessType())
                .businessNumber(insertSeller.getBusinessNumber())
                .createdDate(insertSeller.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .acceptCheck(insertSeller.getAcceptCheck())
                .businessAddress(insertSeller.getBusinessAddress())
                .businessCallNumber(insertSeller.getBusinessCallNumber())
                .message(insertSeller.getMessage())
                .build();
    }

    @Override
    @Cacheable("sellerCache")
    public List<SellerListDto> showSellerList(Pageable pageable) {
        List<Seller> sellerList = sellerRepository.showSellerListNonSubmit(pageable);
        return sellerList.stream()
                .map(seller ->
                        SellerListDto.builder()
                                .id(seller.getId())
                                .businessCEOName(seller.getBusinessCEOName())
                                .companyName(seller.getCompanyName())
                                .businessType(seller.getBusinessType())
                                .businessNumber(seller.getBusinessNumber())
                                .createdDate(seller.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).build())
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict("sellerCache")
    public SellerDto showSellerDetail(Long sellerId) {
        Seller findSeller = Optional.ofNullable(sellerRepository.findSellerBySellerId(sellerId))
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.NO_DATA, "해당 셀러는 삭제된 셀러이거나 존재하지 않은 셀러 입니다."));
        return SellerDto.builder()
                .id(findSeller.getId())
                .businessCEOName(findSeller.getBusinessCEOName())
                .companyName(findSeller.getCompanyName())
                .businessType(findSeller.getBusinessType())
                .businessNumber(findSeller.getBusinessNumber())
                .createdDate(findSeller.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .acceptCheck(findSeller.getAcceptCheck())
                .businessAddress(findSeller.getBusinessAddress())
                .businessCallNumber(findSeller.getBusinessCallNumber())
                .message(findSeller.getMessage())
                .build();
    }

    @Override
    @CacheEvict("sellerCache")
    public SellerIdDto submitSeller(Long sellerId) {
        Seller findSeller = Optional.ofNullable(sellerRepository.findSellerBySellerId(sellerId))
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.NO_DATA, "해당 셀러는 삭제된 셀러이거나 존재하지 않은 셀러 입니다."));

        findSeller.setAcceptCheck(true);
        sellerRepository.save(findSeller);
        return SellerIdDto.builder().id(findSeller.getId()).acceptCheck(findSeller.getAcceptCheck()).build();
    }

    @Override
    @CacheEvict("sellerCache")
    public SellerSubmitFailDto notSubmitSeller(Long sellerId, String message) {
        Seller findSeller = Optional.ofNullable(sellerRepository.findSellerBySellerId(sellerId))
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.NO_DATA, "해당 셀러는 삭제된 셀러이거나 존재하지 않은 셀러 입니다."));

        sellerRepository.sellerUpdateMessage(sellerId, message);

        return SellerSubmitFailDto.builder().id(findSeller.getId()).acceptCheck(findSeller.getAcceptCheck())
                .message(message).build();
    }
}
