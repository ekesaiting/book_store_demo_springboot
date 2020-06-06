package com.feng.book_store_demo.repository;

import com.feng.book_store_demo.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author shf
 * @Date 2020/6/2 12:38
 */
public interface OrderMasterRepository  extends JpaRepository<OrderMaster,String> {
}
