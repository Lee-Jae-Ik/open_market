package com.ontacthealth.shoppingmall.item.service.impl;

import com.ontacthealth.shoppingmall.exception.ShoppingApiRuntimeException;
import com.ontacthealth.shoppingmall.item.model.schema.Category;
import com.ontacthealth.shoppingmall.item.repository.CategoryRepository;
import com.ontacthealth.shoppingmall.item.service.ItemService;
import com.ontacthealth.shoppingmall.item.model.dto.ItemSaveDto;
import com.ontacthealth.shoppingmall.base_model.response.ShoppingApiResult;
import com.ontacthealth.shoppingmall.item.model.schema.Item;
import com.ontacthealth.shoppingmall.image.model.schema.ItemImage;
import com.ontacthealth.shoppingmall.image.repository.ItemImageRepository;
import com.ontacthealth.shoppingmall.item.repository.ItemRepository;
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
    private final CategoryRepository categoryRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ItemImageRepository itemImageRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.itemImageRepository = itemImageRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Item saveItem(ItemSaveDto itemSaveDto) {

        if (itemSaveDto.getItemStock() == 0) {
            throw new ShoppingApiRuntimeException(ShoppingApiResult.NOT_INSERT_STOCK);
        } else if (itemSaveDto.getItemPrice() < 100) {
            throw new ShoppingApiRuntimeException(ShoppingApiResult.NOT_INSERT_PRICE);
        }

        Category findCategory = categoryRepository.findById(itemSaveDto.getCategoryId())
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.WRONG_CATEGORY_ID));

        Item insertItem = Item.builder()
                .itemName(itemSaveDto.getItemName())
                .itemPrice(itemSaveDto.getItemPrice())
                .itemStock(itemSaveDto.getItemStock())
                .category(findCategory)
                .build();
        itemRepository.flush();

        ItemImage findItemImage = itemImageRepository.findItemImageByItemId(insertItem.getId());
        insertItem.setItemImage(findItemImage);
        itemRepository.save(insertItem);

        return insertItem;
    }
}
