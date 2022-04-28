package com.ontacthealth.shoppingmall.repository;

import com.ontacthealth.shoppingmall.model.schema.ItemImage;
import com.ontacthealth.shoppingmall.repository.custom.ItemImageRepositoryCustom;
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
