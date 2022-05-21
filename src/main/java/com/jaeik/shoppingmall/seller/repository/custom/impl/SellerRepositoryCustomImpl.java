package com.jaeik.shoppingmall.seller.repository.custom.impl;

import com.jaeik.shoppingmall.seller.model.schema.QSeller;
import com.jaeik.shoppingmall.seller.model.schema.Seller;
import com.jaeik.shoppingmall.seller.repository.custom.SellerRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SellerRepositoryCustomImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/05/14
 */
@Transactional
public class SellerRepositoryCustomImpl implements SellerRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public SellerRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Seller> showSellerListNonSubmit(Pageable pageable) {
        return jpaQueryFactory
                .select(QSeller.seller)
                .from(QSeller.seller)
                .where(QSeller.seller.acceptCheck.isFalse())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public void sellerUpdateMessage(Long sellerId,String message) {
        jpaQueryFactory
                .update(QSeller.seller)
                .set(QSeller.seller.message, message)
                .where(QSeller.seller.id.eq(sellerId))
                .execute();
    }
}
