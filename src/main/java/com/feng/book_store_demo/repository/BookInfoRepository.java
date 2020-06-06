package com.feng.book_store_demo.repository;

import com.feng.book_store_demo.entity.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/6/2 12:35
 */
public interface BookInfoRepository extends JpaRepository<BookInfo,Integer> {
        List<BookInfo>  findBookInfoByCategoryType(Integer id);
}
