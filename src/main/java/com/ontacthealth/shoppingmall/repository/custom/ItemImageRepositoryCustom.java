package com.ontacthealth.shoppingmall.repository.custom;

import com.ontacthealth.shoppingmall.model.schema.ItemImage;

/**
 * ItemImageRepositoryCustom
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
public interface ItemImageRepositoryCustom {
    ItemImage findItemImageByItemId(Long itemId);
}
