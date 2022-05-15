package com.ontacthealth.shoppingmall.seller.service.impl;

import com.ontacthealth.shoppingmall.base_model.response.ShoppingApiResult;
import com.ontacthealth.shoppingmall.exception.ShoppingApiRuntimeException;
import com.ontacthealth.shoppingmall.seller.model.dto.SellerIdDto;
import com.ontacthealth.shoppingmall.seller.model.dto.SellerListDto;
import com.ontacthealth.shoppingmall.seller.model.dto.SellerSignUpDto;
import com.ontacthealth.shoppingmall.seller.model.dto.SellerSubmitFailDto;
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
    public Seller saveSeller(SellerSignUpDto sellerSignUpDto) {

        Optional<Seller> findSeller = Optional.ofNullable(sellerRepository.findSellerByBusinessNumber(sellerSignUpDto.getBusinessNumber()));
        if (findSeller.isPresent()) {
            throw new ShoppingApiRuntimeException(ShoppingApiResult.DUPLICATION_SELLER_ID);
        }

        if (Boolean.FALSE.equals(SellerUtils.checkValidationSeller(sellerSignUpDto.getBusinessType()))) {
            throw new ShoppingApiRuntimeException(ShoppingApiResult.WRONG_BUSINESS_TYPE);
        }

        Seller insertSeller = Seller.builder()
                .businessNumber(sellerSignUpDto.getBusinessNumber())
                .businessAddress(sellerSignUpDto.getBusinessAddress())
                .businessCEOName(sellerSignUpDto.getBusinessCEOName())
                .businessCallNumber(sellerSignUpDto.getBusinessCallNumber())
                .businessType(sellerSignUpDto.getBusinessType())
                .companyName(sellerSignUpDto.getCompanyName())
                .acceptCheck(false)
                .createdDate(LocalDateTime.now())
                .build();

        return sellerRepository.save(insertSeller);
    }

    @Override
    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            readOnly = true
    )
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
                                .businessCEOName(seller.getBusinessCEOName())
                                .companyName(seller.getCompanyName())
                                .businessType(seller.getBusinessType())
                                .businessNumber(seller.getBusinessNumber())
                                .createdDate(seller.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            readOnly = true
    )
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

    @Override
    @Transactional(rollbackFor = {
            NullPointerException.class,
            IllegalAccessException.class
    })
    public SellerSubmitFailDto notSubmitSeller(Long sellerId, String message) {
        Seller findSeller = Optional.ofNullable(sellerRepository.findSellerBySellerId(sellerId))
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.NO_DATA, "해당 셀러는 삭제된 셀러이거나 존재하지 않은 셀러 입니다."));

        sellerRepository.sellerUpdateMessage(sellerId, message);

        return SellerSubmitFailDto.builder().id(findSeller.getId()).acceptCheck(findSeller.getAcceptCheck())
                .message(message).build();
    }
}
