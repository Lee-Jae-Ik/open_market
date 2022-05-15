package com.ontacthealth.shoppingmall.seller.controller;

import com.ontacthealth.shoppingmall.base_model.response.ShoppingResponse;
import com.ontacthealth.shoppingmall.controller.base.BaseController;
import com.ontacthealth.shoppingmall.seller.model.dto.SellerSignUpDto;
import com.ontacthealth.shoppingmall.seller.model.schema.Seller;
import com.ontacthealth.shoppingmall.seller.service.SellerService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ShoppingResponse> signUpSeller(@RequestBody SellerSignUpDto sellerSignUpDto) {
        return responseApi(sellerService.saveSeller(sellerSignUpDto));
    }

    @GetMapping("/seller")
    public ResponseEntity<ShoppingResponse> showSellerList(Pageable pageable) {
        return responseApi(sellerService.showSellerList(pageable));
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<ShoppingResponse> showSellerDetail(@PathVariable(value = "sellerId") Long sellerId) {
        return responseApi(sellerService.showSellerDetail(sellerId));
    }

    @PostMapping("/seller/submit")
    public ResponseEntity<ShoppingResponse> submitSeller(@RequestParam Long sellerId) {
        return responseApi(sellerService.submitSeller(sellerId));
    }

    @PostMapping("/seller/submit/not")
    public ResponseEntity<ShoppingResponse> notSubmitSeller(@RequestParam(value = "sellerId") Long sellerId,
                                                            @RequestParam(value = "message", required = false) String message) {
        return responseApi(sellerService.notSubmitSeller(sellerId, message));
    }
}
