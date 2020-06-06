package com.feng.book_store_demo.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class OrderMaster {
    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private Integer bookId;
    private String bookName;
    private Integer bookQuantity;
    private String bookIcon;
    private Integer specsId;
    private String specsName;
    private Integer specsPrice;
    private Integer orderAmount;
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;
}
