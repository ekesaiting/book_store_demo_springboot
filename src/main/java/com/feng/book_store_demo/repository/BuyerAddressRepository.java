package com.feng.book_store_demo.repository;

import com.feng.book_store_demo.entity.BuyerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author shf
 * @Date 2020/6/2 12:37
 */
public interface BuyerAddressRepository  extends JpaRepository<BuyerAddress,Integer> {
}
