package com.jaeik.shoppingmall.item.controller;

import com.jaeik.shoppingmall.base_model.response.ShoppingResponse;
import com.jaeik.shoppingmall.controller.base.BaseController;
import com.jaeik.shoppingmall.item.model.dto.ItemSaveDto;
import com.jaeik.shoppingmall.item.service.ItemService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ItemController
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/05/07
 */
@RestController
public class ItemController extends BaseController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/item")
    public ResponseEntity<ShoppingResponse> saveItem(@RequestBody ItemSaveDto itemSaveDto) {
        return responseApi(itemService.saveItem(itemSaveDto));
    }

    @GetMapping("/item")
    public ResponseEntity<ShoppingResponse> showItemList(@RequestParam(required = false) Long categoryId, @PageableDefault(size = 10) Pageable pageable) {
        return responseApi(itemService.showItemList(categoryId, pageable));
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<ShoppingResponse> showItemDetail(@PathVariable(value = "itemId") Long itemId) {
        return responseApi(itemService.showItemDetail(itemId));
    }

    @GetMapping("/item/name/{itemName}")
    public ResponseEntity<ShoppingResponse> showItemSearchItemName(@PathVariable(value = "itemName") String itemName, Pageable pageable) {
        return responseApi(itemService.showItemListByItemName(itemName,pageable));
    }
}
