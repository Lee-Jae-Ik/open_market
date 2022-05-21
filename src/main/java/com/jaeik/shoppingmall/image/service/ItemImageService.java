package com.jaeik.shoppingmall.image.service;

import com.jaeik.shoppingmall.image.model.dto.ItemImageIdDto;
import com.jaeik.shoppingmall.image.model.dto.ItemImageSaveDto;

/**
 * ItemImageService
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
public interface ItemImageService {
    ItemImageIdDto saveItemImage(ItemImageSaveDto itemImageSaveDto);
}
