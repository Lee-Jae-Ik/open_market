package com.ontacthealth.shoppingmall.seller.model.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ontacthealth.shoppingmall.item.model.schema.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Seller
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-05-14
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "business_type")
    private String businessType;

    @Column(name = "business_number")
    private String businessNumber;

    @Column(name = "business_address")
    private String businessAddress;

    @Column(name = "business_CEO_name")
    private String businessCEOName;

    @Column(name = "business_call_number")
    private String businessCallNumber;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<Item> itemList;

    @Column(name = "accept_check")
    @Setter
    private Boolean acceptCheck = false;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "message")
    @Setter
    private String message;

    @Builder
    public Seller(Long id, String companyName, String businessType, String businessNumber, String businessAddress, String businessCEOName, String businessCallNumber, List<Item> itemList, Boolean acceptCheck, LocalDateTime createdDate, String message) {
        this.id = id;
        this.companyName = companyName;
        this.businessType = businessType;
        this.businessNumber = businessNumber;
        this.businessAddress = businessAddress;
        this.businessCEOName = businessCEOName;
        this.businessCallNumber = businessCallNumber;
        this.itemList = itemList;
        this.acceptCheck = acceptCheck;
        this.createdDate = createdDate;
        this.message = message;
    }
}
