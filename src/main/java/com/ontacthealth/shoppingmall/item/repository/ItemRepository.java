package com.ontacthealth.shoppingmall.item.repository;

import com.ontacthealth.shoppingmall.item.repository.custom.ItemRepositoryCustom;
import com.ontacthealth.shoppingmall.item.model.schema.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ItemRepository
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
    List<Item> findItemsByCategory_Id(Long categoryId, Pageable pageable);
}
