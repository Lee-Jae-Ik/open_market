package com.jaeik.shoppingmall.item.service;

import com.jaeik.shoppingmall.item.model.dto.ItemDetailDto;
import com.jaeik.shoppingmall.item.model.dto.ItemDto;
import com.jaeik.shoppingmall.item.model.dto.ItemSaveDto;
import com.jaeik.shoppingmall.item.model.schema.Item;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * ItemService
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
public interface ItemService {
    ItemDto saveItem(ItemSaveDto itemSaveDto);

    List<Item> showItemList(Long categoryId, Pageable pageable);
    List<Item> showItemListByItemName(String itemName, Pageable pageable);

    ItemDetailDto showItemDetail(Long itemId);
}
