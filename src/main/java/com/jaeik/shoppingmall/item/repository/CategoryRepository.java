package com.jaeik.shoppingmall.item.repository;

import com.jaeik.shoppingmall.item.model.schema.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CategoryRepository
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/05/07
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
