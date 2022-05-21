package com.jaeik.shoppingmall.seller.repository;

import com.jaeik.shoppingmall.seller.model.schema.Seller;
import com.jaeik.shoppingmall.seller.repository.custom.SellerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
public interface SellerRepository extends JpaRepository<Seller, Long>, SellerRepositoryCustom {

    @Query("select s from Seller s where s.id = :sellerId and s.acceptCheck = false")
    Seller findSellerBySellerId(@Param("sellerId") Long sellerId);

    Seller findSellerByBusinessNumber(String businessNumber);
}
