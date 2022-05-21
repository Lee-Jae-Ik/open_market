package com.jaeik.shoppingmall.image.controller;

import com.jaeik.shoppingmall.base_model.response.ShoppingResponse;
import com.jaeik.shoppingmall.controller.base.BaseController;
import com.jaeik.shoppingmall.image.model.dto.ItemImageSaveDto;
import com.jaeik.shoppingmall.image.service.ItemImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ImageController
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/05/07
 */
@RestController
public class ImageController extends BaseController {

    private final ItemImageService itemImageService;

    public ImageController(ItemImageService itemImageService) {
        this.itemImageService = itemImageService;
    }

    @PostMapping("/item/image")
    public ResponseEntity<ShoppingResponse> saveItemImage(@RequestBody ItemImageSaveDto itemImageSaveDto){
        return responseApi(itemImageService.saveItemImage(itemImageSaveDto));
    }
}
