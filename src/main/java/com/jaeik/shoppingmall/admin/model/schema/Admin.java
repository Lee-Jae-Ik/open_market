package com.jaeik.shoppingmall.admin.model.schema;

import com.jaeik.shoppingmall.admin.model.role.AdminRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Admin
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-21
 */
@Getter
@NoArgsConstructor
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_id")
    private String adminId;

    @Column(name = "admin_password")
    private String adminPassword;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "admin_role")
    private AdminRole adminRole;

    @Builder
    public Admin(Long id, String adminId, String adminPassword, String adminName, AdminRole adminRole) {
        this.id = id;
        this.adminId = adminId;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
        this.adminRole = adminRole;
    }
}
