package com.ontacthealth.shoppingmall.item.model.schema;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Category
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022/05/07
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "total_count")
    @Setter
    private Integer totalCount;

    @Builder
    public Category(Long id, String categoryName, Integer totalCount) {
        this.id = id;
        this.categoryName = categoryName;
        this.totalCount = totalCount;
    }
}
