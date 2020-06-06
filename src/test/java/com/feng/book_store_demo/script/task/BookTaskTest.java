package com.feng.book_store_demo.script.task;

import com.feng.book_store_demo.repository.BookInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author shf
 * @Date 2020/6/2 14:19
 */
@SpringBootTest
class BookTaskTest {

    @Autowired
    private BookTask bookTask;
    @Autowired
    private BookInfoRepository bookInfoRepository;
    @Test
    void download() {
        bookTask.download();
    }
   @Test
    public void test(){
        bookInfoRepository.findAll();
   }


}