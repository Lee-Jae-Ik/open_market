package com.ontacthealth.shoppingmall.image.service.impl;

import com.ontacthealth.shoppingmall.image.model.dto.ItemImageIdDto;
import com.ontacthealth.shoppingmall.image.model.dto.ItemImageSaveDto;
import com.ontacthealth.shoppingmall.image.model.schema.ItemImage;
import com.ontacthealth.shoppingmall.image.repository.ItemImageRepository;
import com.ontacthealth.shoppingmall.image.service.ItemImageService;
import com.ontacthealth.shoppingmall.item.repository.ItemRepository;
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
    private final ItemRepository itemRepository;

    public ItemImageServiceImpl(ItemImageRepository itemImageRepository, ItemRepository itemRepository) {
        this.itemImageRepository = itemImageRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemImageIdDto saveItemImage(ItemImageSaveDto itemImageSaveDto) {

        ItemImage insertItemImage = ItemImage.builder()
                .thumb1(itemImageSaveDto.getThumb1())
                .thumb2(itemImageSaveDto.getThumb2())
                .thumb3(itemImageSaveDto.getThumb3())
                .thumb4(itemImageSaveDto.getThumb4())
                .thumb5(itemImageSaveDto.getThumb5())
                .detail(itemImageSaveDto.getDetail())
                .build();
        itemImageRepository.save(insertItemImage);

        return ItemImageIdDto.builder().id(insertItemImage.getId()).build();
    }
}
