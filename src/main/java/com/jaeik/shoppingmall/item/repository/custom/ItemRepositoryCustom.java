package com.jaeik.shoppingmall.item.repository.custom;

import com.jaeik.shoppingmall.item.model.schema.Item;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * ItemRepositoryCustom
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
public interface ItemRepositoryCustom {

    List<Item> showItemListForCategoryId(Long categoryId, Pageable pageable);
    List<Item> showItemListForItemName(String itemName, Pageable pageable);
}
