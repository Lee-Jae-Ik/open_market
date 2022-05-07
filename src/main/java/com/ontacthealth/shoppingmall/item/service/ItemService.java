package com.ontacthealth.shoppingmall.item.service;

import com.ontacthealth.shoppingmall.item.model.dto.ItemSaveDto;
import com.ontacthealth.shoppingmall.item.model.schema.Item;

/**
 * ItemService
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
public interface ItemService {
    Item saveItem(ItemSaveDto itemSaveDto);
}
