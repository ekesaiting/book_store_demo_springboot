package com.feng.book_store_demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class BookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String bookName;
    private Integer bookPrice;
    private String bookDescription;
    private Integer bookStock;
    private String bookIcon;
    private Integer categoryType;
    private String bookTag;
    private Date createTime;
    private Date updateTime;
}
