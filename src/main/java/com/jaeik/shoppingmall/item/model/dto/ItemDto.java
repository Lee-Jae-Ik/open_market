package com.jaeik.shoppingmall.item.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * ItemDto
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
public class ItemDto {

    private Long id;

    private String itemName;

    private int itemPrice;

    private int itemStock;

    private Long itemImageId;

    private Long categoryId;

    private Long sellerId;

    @Builder
    public ItemDto(Long id, String itemName, int itemPrice, int itemStock, Long itemImageId, Long categoryId, Long sellerId) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
        this.itemImageId = itemImageId;
        this.categoryId = categoryId;
        this.sellerId = sellerId;
    }
}
