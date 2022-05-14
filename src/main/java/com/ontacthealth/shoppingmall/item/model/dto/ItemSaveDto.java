package com.ontacthealth.shoppingmall.item.model.dto;

import com.ontacthealth.shoppingmall.image.model.schema.ItemImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * ItemSaveDto
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
@Getter
@NoArgsConstructor
public class ItemSaveDto {

    private String itemName;
    private Long sellerId;
    private int itemPrice;
    private int itemStock;
    private Long categoryId;
    private Long itemImageId;

    @Builder
    public ItemSaveDto(String itemName, Long sellerId, int itemPrice, int itemStock, Long categoryId, Long itemImageId) {
        this.itemName = itemName;
        this.sellerId = sellerId;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
        this.categoryId = categoryId;
        this.itemImageId = itemImageId;
    }
}
