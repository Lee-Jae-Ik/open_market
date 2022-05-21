package com.jaeik.shoppingmall.admin.controller;

import com.jaeik.shoppingmall.admin.model.schema.Admin;
import com.jaeik.shoppingmall.admin.service.AdminService;
import com.jaeik.shoppingmall.base_model.response.ShoppingResponse;
import com.jaeik.shoppingmall.controller.base.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminController
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-21
 */
@RestController
public class AdminController extends BaseController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin")
    public ResponseEntity<ShoppingResponse> saveAdmin(@RequestBody Admin admin) {
        return responseApi(adminService.saveAdmin(admin));
    }
}
