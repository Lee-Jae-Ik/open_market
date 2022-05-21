package com.jaeik.shoppingmall.admin.service.impl;

import com.jaeik.shoppingmall.admin.model.schema.Admin;
import com.jaeik.shoppingmall.admin.repository.AdminRepository;
import com.jaeik.shoppingmall.admin.service.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AdminServiceImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-21
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(Admin.builder()
                        .adminId(admin.getAdminId())
                        .adminPassword(passwordEncoder.encode(admin.getAdminPassword()))
                        .adminRole(admin.getAdminRole())
                        .adminName(admin.getAdminName())
                .build());
    }
}
