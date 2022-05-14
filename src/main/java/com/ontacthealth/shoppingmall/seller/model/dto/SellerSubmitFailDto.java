package com.ontacthealth.shoppingmall.seller.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * SellerSubmitFailDto
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
public class SellerSubmitFailDto {

    private Long id;
    private Boolean acceptCheck;
    private String message;

    @Builder
    public SellerSubmitFailDto(Long id, Boolean acceptCheck, String message) {
        this.id = id;
        this.acceptCheck = acceptCheck;
        this.message = message;
    }
}
