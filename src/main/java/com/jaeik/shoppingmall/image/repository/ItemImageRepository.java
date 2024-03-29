package com.jaeik.shoppingmall.image.repository;

import com.jaeik.shoppingmall.image.repository.custom.ItemImageRepositoryCustom;
import com.jaeik.shoppingmall.image.model.schema.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ItemImageRepository
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
@Repository
public interface ItemImageRepository extends JpaRepository<ItemImage, Long>, ItemImageRepositoryCustom {
}
