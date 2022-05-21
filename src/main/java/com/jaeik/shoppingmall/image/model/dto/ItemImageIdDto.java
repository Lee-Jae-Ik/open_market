package com.jaeik.shoppingmall.image.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * ItemImageIdDto
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/05/07
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@NoArgsConstructor
public class ItemImageIdDto {

    private Long id;

    @Builder
    public ItemImageIdDto(Long id) {
        this.id = id;
    }
}
