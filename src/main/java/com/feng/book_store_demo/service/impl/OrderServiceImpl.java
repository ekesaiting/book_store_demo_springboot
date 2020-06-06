package com.feng.book_store_demo.service.impl;

import com.feng.book_store_demo.constant.enums.OrderEnum;
import com.feng.book_store_demo.dto.OrderDTO;
import com.feng.book_store_demo.entity.BookInfo;
import com.feng.book_store_demo.entity.BookSpecs;
import com.feng.book_store_demo.entity.OrderMaster;
import com.feng.book_store_demo.exception.OrderException;
import com.feng.book_store_demo.repository.BookInfoRepository;
import com.feng.book_store_demo.repository.BookSpecsRepository;
import com.feng.book_store_demo.repository.OrderMasterRepository;
import com.feng.book_store_demo.service.BookService;
import com.feng.book_store_demo.service.OrderService;
import com.feng.book_store_demo.util.KeyUtil;
import com.feng.book_store_demo.util.StringFormat;
import com.feng.book_store_demo.vo.OrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @Author shf
 * @Date 2020/6/5 16:31
 */
@Service
@Slf4j
public class OrderServiceImpl  implements OrderService {
    @Autowired
    private BookInfoRepository bookInfoRepository;
    @Autowired
    private BookSpecsRepository bookSpecsRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private BookService bookService;


    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        BookSpecs bookSpecs = bookSpecsRepository.getOne(orderDTO.getSpecsId());
        BookInfo bookInfo = bookInfoRepository.getOne(bookSpecs.getBookId());
        orderMaster.setOrderId(KeyUtil.createUniqueKey());
        orderMaster.setBuyerName(orderDTO.getBuyerName());
        orderMaster.setBuyerPhone(orderDTO.getBuyerPhone());
        orderMaster.setBuyerAddress(orderDTO.getBuyerAddress());
        orderMaster.setBookId(bookSpecs.getBookId());
        orderMaster.setBookName(bookInfo.getBookName());
        orderMaster.setBookQuantity(orderDTO.getBookQuantity());
        orderMaster.setBookIcon(bookSpecs.getSpecsIcon());
        orderMaster.setSpecsId(orderDTO.getSpecsId());
        orderMaster.setSpecsName(bookSpecs.getSpecsName());
        orderMaster.setSpecsPrice(bookSpecs.getSpecsPrice());
        BigDecimal orderAmount = new BigDecimal(0)
                .add(BigDecimal.valueOf(bookSpecs.getSpecsPrice() / 100.0))
                .multiply(new BigDecimal(orderDTO.getBookQuantity()))
                .add(new BigDecimal(OrderEnum.ORDER_FREIGHT.getCode()))
                .multiply(new BigDecimal(100));
        orderMaster.setOrderAmount(orderAmount.intValue());
        orderMaster.setPayStatus(OrderEnum.UNPAID.getCode());
        bookService.subStock(orderDTO.getSpecsId(),orderDTO.getBookQuantity());
        orderMasterRepository.save(orderMaster);
        orderDTO.setOrderId(orderMaster.getOrderId());
        return orderDTO;
    }

    @Override
    public OrderDetailVO getOrderDetailByOrderId(String orderId) {
        Optional<OrderMaster> orderMasterOpt = orderMasterRepository.findById(orderId);
        if (!orderMasterOpt.isPresent()){
            log.error("【订单异常】订单不存在,订单id: {}",orderId);
            throw new OrderException(OrderEnum.ORDER_NOT_EXIST);
        }
        OrderMaster orderMaster = orderMasterOpt.get();
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setOrderId(orderMaster.getOrderId());
        orderDetailVO.setBuyerName(orderMaster.getBuyerName());
        orderDetailVO.setTel(orderMaster.getBuyerPhone());
        orderDetailVO.setAddress(orderMaster.getBuyerAddress());
        orderDetailVO.setNum(orderMaster.getBookQuantity());
        orderDetailVO.setBookName(orderMaster.getBookName());
        orderDetailVO.setSpecs(orderMaster.getSpecsName());
        orderDetailVO.setPrice(StringFormat.formatDecimal(orderMaster.getSpecsPrice()/100.0));
        orderDetailVO.setAmount(BigDecimal.valueOf(orderMaster.getOrderAmount()/100.0));
        orderDetailVO.setIcon("../images/"+orderMaster.getBookIcon());
        orderDetailVO.setPayStatus(orderMaster.getPayStatus());
        orderDetailVO.setFreight(OrderEnum.ORDER_FREIGHT.getCode());
        return orderDetailVO;
    }

    @Transactional
    @Override
    public String pay(String orderId) {
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()){
            log.error("【订单异常】订单不存在");
            throw new OrderException(OrderEnum.ORDER_NOT_EXIST);
        }
        OrderMaster orderMaster = orderMasterOptional.get();

        if(orderMaster.getPayStatus().equals(OrderEnum.UNPAID.getCode())){
            orderMaster.setPayStatus(OrderEnum.PAID.getCode());
            orderMasterRepository.save(orderMaster);
        } else {
            log.error("【支付订单】订单已支付,orderMaster={}",orderMaster);
        }
        return orderId;
    }
}
