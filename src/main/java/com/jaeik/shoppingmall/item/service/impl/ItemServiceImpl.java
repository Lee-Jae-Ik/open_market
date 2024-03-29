package com.jaeik.shoppingmall.item.service.impl;

import com.jaeik.shoppingmall.exception.ShoppingApiRuntimeException;
import com.jaeik.shoppingmall.item.model.dto.ItemDetailDto;
import com.jaeik.shoppingmall.item.model.dto.ItemDto;
import com.jaeik.shoppingmall.item.model.schema.Category;
import com.jaeik.shoppingmall.item.repository.CategoryRepository;
import com.jaeik.shoppingmall.item.service.ItemService;
import com.jaeik.shoppingmall.item.model.dto.ItemSaveDto;
import com.jaeik.shoppingmall.base_model.response.ShoppingApiResult;
import com.jaeik.shoppingmall.item.model.schema.Item;
import com.jaeik.shoppingmall.image.model.schema.ItemImage;
import com.jaeik.shoppingmall.image.repository.ItemImageRepository;
import com.jaeik.shoppingmall.item.repository.ItemRepository;
import com.jaeik.shoppingmall.seller.model.schema.Seller;
import com.jaeik.shoppingmall.seller.repository.SellerRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * ItemServiceImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;
    private final CategoryRepository categoryRepository;
    private final SellerRepository sellerRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ItemImageRepository itemImageRepository, CategoryRepository categoryRepository, SellerRepository sellerRepository) {
        this.itemRepository = itemRepository;
        this.itemImageRepository = itemImageRepository;
        this.categoryRepository = categoryRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    @CacheEvict("itemCache")
    public ItemDto saveItem(ItemSaveDto itemSaveDto) {

        if (itemSaveDto.getItemStock() == 0) {
            throw new ShoppingApiRuntimeException(ShoppingApiResult.NOT_INSERT_STOCK);
        } else if (itemSaveDto.getItemPrice() < 100) {
            throw new ShoppingApiRuntimeException(ShoppingApiResult.NOT_INSERT_PRICE);
        }

        Category findCategory = categoryRepository.findById(itemSaveDto.getCategoryId())
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.WRONG_CATEGORY_ID));

        Seller findSeller = Optional.ofNullable(sellerRepository.findSellerBySellerId(itemSaveDto.getSellerId()))
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.NO_DATA,"해당 셀러는 존재하지 않습니다."));

        Item insertItem = Item.builder()
                .itemName(itemSaveDto.getItemName())
                .itemPrice(itemSaveDto.getItemPrice())
                .itemStock(itemSaveDto.getItemStock())
                .category(findCategory)
                .seller(findSeller)
                .build();
        itemRepository.save(insertItem);


        ItemImage findItemImage = itemImageRepository.findById(itemSaveDto.getItemImageId())
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.NO_DATA));
        insertItem.setItemImage(findItemImage);
        itemRepository.save(insertItem);
        findItemImage.setItemId(insertItem.getId());
        itemImageRepository.save(findItemImage);

        return ItemDto.builder()
                .id(insertItem.getId())
                .itemImageId(insertItem.getItemImage().getId())
                .categoryId(insertItem.getCategory().getId())
                .sellerId(insertItem.getSeller().getId())
                .itemName(insertItem.getItemName())
                .itemPrice(insertItem.getItemPrice())
                .itemStock(insertItem.getItemStock())
                .build();
    }

    @Override
    @Cacheable("itemCache")
    public List<Item> showItemList(Long categoryId, Pageable pageable) {
        return Optional.ofNullable(itemRepository.showItemListForCategoryId(categoryId, pageable))
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.NO_DATA));
    }

    @Override
    public List<Item> showItemListByItemName(String itemName, Pageable pageable) {
        return itemRepository.showItemListForItemName(itemName,pageable);
    }

    @Override
    public ItemDetailDto showItemDetail(Long itemId) {
        Item findItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.NO_DATA, "삭제 되었거나 존재하지 않은 상품 입니다."));
        Category findCategory = categoryRepository.findById(findItem.getCategory().getId())
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.NO_DATA));
        ItemImage findItemImage = itemImageRepository.findById(findItem.getItemImage().getId())
                .orElseThrow(() -> new ShoppingApiRuntimeException(ShoppingApiResult.NO_DATA));

        return ItemDetailDto.builder()
                .itemName(findItem.getItemName())
                .itemPrice(findItem.getItemPrice())
                .itemStock(findItem.getItemStock())
                .categoryName(findCategory.getCategoryName())
                .detail(findItemImage.getDetail())
                .thumb1(findItemImage.getThumb1())
                .thumb2(findItemImage.getThumb2())
                .thumb3(findItemImage.getThumb3())
                .thumb4(findItemImage.getThumb4())
                .thumb5(findItemImage.getThumb5())
                .build();
    }
}
