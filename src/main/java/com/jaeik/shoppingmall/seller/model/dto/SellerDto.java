package com.jaeik.shoppingmall.seller.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * SellerSaveDto
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-15
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@NoArgsConstructor
public class SellerDto {

    private Long id;
    private String companyName;
    private String businessType;
    private String businessNumber;
    private String businessAddress;
    private String businessCEOName;
    private String businessCallNumber;
    private Boolean acceptCheck;
    private String createdDate;
    private String message;

    @Builder
    public SellerDto(Long id, String companyName, String businessType, String businessNumber, String businessAddress, String businessCEOName, String businessCallNumber, Boolean acceptCheck, String createdDate, String message) {
        this.id = id;
        this.companyName = companyName;
        this.businessType = businessType;
        this.businessNumber = businessNumber;
        this.businessAddress = businessAddress;
        this.businessCEOName = businessCEOName;
        this.businessCallNumber = businessCallNumber;
        this.acceptCheck = acceptCheck;
        this.createdDate = createdDate;
        this.message = message;
    }
}
