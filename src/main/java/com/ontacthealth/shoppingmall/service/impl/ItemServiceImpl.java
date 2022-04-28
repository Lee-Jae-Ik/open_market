package com.ontacthealth.shoppingmall.service.impl;

import com.ontacthealth.shoppingmall.exception.OntactApiRuntimeException;
import com.ontacthealth.shoppingmall.model.dto.ItemSaveDto;
import com.ontacthealth.shoppingmall.model.response.OntactApiResult;
import com.ontacthealth.shoppingmall.model.schema.Item;
import com.ontacthealth.shoppingmall.model.schema.ItemImage;
import com.ontacthealth.shoppingmall.repository.ItemImageRepository;
import com.ontacthealth.shoppingmall.repository.ItemRepository;
import com.ontacthealth.shoppingmall.service.ItemService;
import org.springframework.stereotype.Service;

/**
 * ItemServiceImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ItemImageRepository itemImageRepository) {
        this.itemRepository = itemRepository;
        this.itemImageRepository = itemImageRepository;
    }

    @Override
    public Item saveItem(ItemSaveDto itemSaveDto) {

        if (itemSaveDto.getItemStock() == 0) {
            throw new OntactApiRuntimeException(OntactApiResult.NOT_INSERT_STOCK);
        } else if (itemSaveDto.getItemPrice() < 100) {
            throw new OntactApiRuntimeException(OntactApiResult.NOT_INSERT_PRICE);
        }

        Item insertItem = Item.builder()
                .itemName(itemSaveDto.getItemName())
                .itemPrice(itemSaveDto.getItemPrice())
                .itemStock(itemSaveDto.getItemStock())
                .build();
        itemRepository.flush();

        ItemImage findItemImage = itemImageRepository.findItemImageByItemId(insertItem.getId());
        insertItem.setItemImage(findItemImage);
        itemRepository.save(insertItem);

        return insertItem;
    }
}
