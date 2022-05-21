package com.jaeik.shoppingmall.image.model.schema;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;

/**
 * ItemImage
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-28
 */
@Getter
@NoArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
@Entity
@Table(name = "item_image")
public class ItemImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    @Setter
    private Long itemId;

    @Column(name = "thumb_1")
    private String thumb1;

    @Column(name = "thumb_2")
    private String thumb2;

    @Column(name = "thumb_3")
    private String thumb3;

    @Column(name = "thumb_4")
    private String thumb4;

    @Column(name = "thumb_5")
    private String thumb5;

    @Column(name = "detail", length = 255)
    private String detail;

    @Builder
    public ItemImage(Long id, Long itemId, String thumb1, String thumb2, String thumb3, String thumb4, String thumb5, String detail) {
        this.id = id;
        this.itemId = itemId;
        this.thumb1 = thumb1;
        this.thumb2 = thumb2;
        this.thumb3 = thumb3;
        this.thumb4 = thumb4;
        this.thumb5 = thumb5;
        this.detail = detail;
    }
}
