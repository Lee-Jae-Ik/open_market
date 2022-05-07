package com.ontacthealth.shoppingmall.item.model.schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Category
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/05/07
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "total_count")
    private Integer totalCount;

    @Builder
    public Category(Long id, String categoryName, Integer totalCount) {
        this.id = id;
        this.categoryName = categoryName;
        this.totalCount = totalCount;
    }
}
