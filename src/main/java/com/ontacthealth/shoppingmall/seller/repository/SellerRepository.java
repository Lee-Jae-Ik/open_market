package com.ontacthealth.shoppingmall.seller.repository;

import com.ontacthealth.shoppingmall.seller.model.schema.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * SellerRepository
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-14
 */
@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query("select s from Seller s where s.id = :sellerId and s.acceptCheck = true")
    Seller findSellerBySellerId(Long SellerId);
}
