package com.ontacthealth.shoppingmall.service.impl;

import com.ontacthealth.shoppingmall.model.schema.ItemImage;
import com.ontacthealth.shoppingmall.repository.ItemImageRepository;
import com.ontacthealth.shoppingmall.service.ItemImageService;
import org.springframework.stereotype.Service;

/**
 * ItemImageServiceImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
@Service
public class ItemImageServiceImpl implements ItemImageService {

    private final ItemImageRepository itemImageRepository;

    public ItemImageServiceImpl(ItemImageRepository itemImageRepository) {
        this.itemImageRepository = itemImageRepository;
    }

    @Override
    public ItemImage saveItemImage(ItemImage itemImage) {
        return null;
    }
}
