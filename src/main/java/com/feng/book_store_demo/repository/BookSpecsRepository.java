package com.feng.book_store_demo.repository;

import com.feng.book_store_demo.entity.BookSpecs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/6/4 12:37
 */
public interface BookSpecsRepository  extends JpaRepository<BookSpecs,Integer> {
    List<BookSpecs>  findBookSpecsByBookId(Integer id);
}
