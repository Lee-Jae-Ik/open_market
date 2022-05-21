package com.jaeik.shoppingmall.admin.repository;

import com.jaeik.shoppingmall.admin.model.schema.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AdminRepository
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-21
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
