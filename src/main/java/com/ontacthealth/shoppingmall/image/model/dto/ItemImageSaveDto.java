package com.ontacthealth.shoppingmall.image.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * ItemImageSaveDto
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
public class ItemImageSaveDto {

    private String thumb1;
    private String thumb2;
    private String thumb3;
    private String thumb4;
    private String thumb5;
    private String detail;

    @Builder
    public ItemImageSaveDto(String thumb1, String thumb2, String thumb3, String thumb4, String thumb5, String detail) {
        this.thumb1 = thumb1;
        this.thumb2 = thumb2;
        this.thumb3 = thumb3;
        this.thumb4 = thumb4;
        this.thumb5 = thumb5;
        this.detail = detail;
    }
}
