package com.ontacthealth.shoppingmall.seller.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Column;
import java.time.LocalDateTime;

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
    private String companyName;
    private LocalDateTime createdDate;

    @Builder
    public SellerListDto(Long id, String companyName, LocalDateTime createdDate) {
        this.id = id;
        this.companyName = companyName;
        this.createdDate = createdDate;
    }
}
