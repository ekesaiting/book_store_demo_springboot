package com.feng.book_store_demo.service;

import com.feng.book_store_demo.dto.OrderDTO;
import com.feng.book_store_demo.vo.OrderDetailVO;

import java.util.List;

public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);
    OrderDetailVO getOrderDetailByOrderId(String orderId);
    String pay(String orderId);
    List<OrderDetailVO> orderList();
    void cancel(String id);
}
