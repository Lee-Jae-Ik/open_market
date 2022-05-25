package com.jaeik.shoppingmall.service;

import com.jaeik.shoppingmall.item.model.dto.ItemDetailDto;
import com.jaeik.shoppingmall.item.model.schema.Item;
import com.jaeik.shoppingmall.item.service.ItemService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ItemServiceTest
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-24
 */
@ActiveProfiles("local")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    @Order(1)
    void 유닛테스트테스트() {
        ItemDetailDto findItem = itemService.showItemDetail(2L);
        Assertions.assertEquals("테스트", findItem.getItemName());
    }
}
