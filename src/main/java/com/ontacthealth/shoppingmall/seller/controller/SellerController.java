package com.ontacthealth.shoppingmall.seller.controller;

import com.ontacthealth.shoppingmall.base_model.response.ShoppingResponse;
import com.ontacthealth.shoppingmall.controller.base.BaseController;
import com.ontacthealth.shoppingmall.seller.model.schema.Seller;
import com.ontacthealth.shoppingmall.seller.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * SellerController
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-14
 */
@RestController
public class SellerController extends BaseController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/seller/signup")
    public ResponseEntity<ShoppingResponse> signUpSeller(@RequestBody Seller seller) {
        return responseApi(sellerService.saveSeller(seller));
    }
}
