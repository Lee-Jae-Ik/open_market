package com.ontacthealth.shoppingmall.model.dto;

import com.ontacthealth.shoppingmall.model.schema.ItemImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Column;
import java.util.List;

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
    private int itemPrice;
    private int itemStock;
    private ItemImage itemImage;

    @Builder
    public ItemSaveDto(String itemName, int itemPrice, int itemStock, ItemImage itemImage) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
        this.itemImage = itemImage;
    }
}
