package com.jaeik.shoppingmall.item.repository.custom.impl;

import com.jaeik.shoppingmall.item.model.schema.Item;
import com.jaeik.shoppingmall.item.model.schema.QCategory;
import com.jaeik.shoppingmall.item.model.schema.QItem;
import com.jaeik.shoppingmall.item.repository.custom.ItemRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;
import java.util.stream.Collectors;

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
    private final ElasticsearchOperations elasticsearchOperations;

    public ItemRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory, ElasticsearchOperations elasticsearchOperations) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.elasticsearchOperations = elasticsearchOperations;
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
        //커밋 목록 테스트
    }

    @Override
    public List<Item> showItemListForItemName(String itemName, Pageable pageable) {
        Criteria criteria = Criteria.where("item.itemName").contains(itemName);
        Query query = new CriteriaQuery(criteria).setPageable(pageable);
        SearchHits<Item> searchHits = elasticsearchOperations.search(query, Item.class);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
