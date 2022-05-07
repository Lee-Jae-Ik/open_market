package com.ontacthealth.shoppingmall.item.repository.custom.impl;

import com.ontacthealth.shoppingmall.item.repository.custom.ItemRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * ItemRepositoryCustomImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public ItemRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
