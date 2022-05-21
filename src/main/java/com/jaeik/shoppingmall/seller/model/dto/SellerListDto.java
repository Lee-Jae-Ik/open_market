package com.jaeik.shoppingmall.seller.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * SellerListDto
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/05/14
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@NoArgsConstructor
public class SellerListDto {

    private Long id;
    private String businessCEOName;
    private String companyName;
    private String businessType;
    private String businessNumber;
    private String createdDate;

    @Builder
    public SellerListDto(Long id, String businessCEOName, String companyName, String businessType, String businessNumber, String createdDate) {
        this.id = id;
        this.businessCEOName = businessCEOName;
        this.companyName = companyName;
        this.businessType = businessType;
        this.businessNumber = businessNumber;
        this.createdDate = createdDate;
    }
}
