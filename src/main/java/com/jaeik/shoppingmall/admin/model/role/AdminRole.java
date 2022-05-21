package com.jaeik.shoppingmall.admin.model.role;

/**
 * AdminRole
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-21
 */
public enum AdminRole {
    ADMIN_BASIC("일반 어드민", 1),
    ADMIN_MASTER("마스터 어드민", 2)
    ;

    private String roleCode;
    private int roleNumber;

    AdminRole(String roleCode, int roleNumber) {
        this.roleCode = roleCode;
        this.roleNumber = roleNumber;
    }
}
