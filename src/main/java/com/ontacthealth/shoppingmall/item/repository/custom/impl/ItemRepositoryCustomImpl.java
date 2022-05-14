package com.ontacthealth.shoppingmall.item.repository.custom.impl;

import com.ontacthealth.shoppingmall.item.model.schema.Item;
import com.ontacthealth.shoppingmall.item.model.schema.QCategory;
import com.ontacthealth.shoppingmall.item.model.schema.QItem;
import com.ontacthealth.shoppingmall.item.repository.custom.ItemRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    @Override
    public List<Item> showItemListForCategoryId(Long categoryId, Pageable pageable) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (categoryId != null) {
            booleanBuilder.and(QItem.item.category.id.eq(categoryId));
        } else {
            booleanBuilder.and(QItem.item.category.id.isNotNull());
        }

        return jpaQueryFactory
                .select(QItem.item)
                .from(QItem.item)
                .join(QItem.item.category,QCategory.category)
                .fetchJoin()
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
