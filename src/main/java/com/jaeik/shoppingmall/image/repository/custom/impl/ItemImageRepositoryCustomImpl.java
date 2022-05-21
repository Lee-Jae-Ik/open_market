package com.jaeik.shoppingmall.image.repository.custom.impl;

import com.jaeik.shoppingmall.image.model.schema.ItemImage;
import com.jaeik.shoppingmall.image.model.schema.QItemImage;
import com.jaeik.shoppingmall.image.repository.custom.ItemImageRepositoryCustom;
import com.jaeik.shoppingmall.item.model.schema.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * ItemImageRepositoryCustomImpl
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
public class ItemImageRepositoryCustomImpl implements ItemImageRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public ItemImageRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public ItemImage findItemImageByItemId(Long itemId) {
        return jpaQueryFactory
                .select(QItemImage.itemImage)
                .from(QItemImage.itemImage)
                .innerJoin(QItemImage.itemImage, QItem.item.itemImage)
                .where(QItemImage.itemImage.itemId.eq(itemId))
                .fetchJoin()
                .fetchOne();
    }
}
