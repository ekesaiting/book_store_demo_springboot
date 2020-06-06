package com.feng.book_store_demo.service;

import com.feng.book_store_demo.dto.OrderDTO;
import com.feng.book_store_demo.vo.OrderDetailVO;

public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);
    OrderDetailVO getOrderDetailByOrderId(String orderId);
    String pay(String orderId);
}
