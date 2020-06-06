package com.feng.book_store_demo.repository;

import com.feng.book_store_demo.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author shf
 * @Date 2020/6/2 12:36
 */
public interface BookCategoryRepository extends JpaRepository<BookCategory,Integer> {
    BookCategory findBookCategoryByCategoryName(String name);
}
