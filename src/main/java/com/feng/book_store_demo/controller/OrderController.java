package com.feng.book_store_demo.controller;


import com.feng.book_store_demo.constant.enums.ResultEnum;
import com.feng.book_store_demo.dto.OrderDTO;
import com.feng.book_store_demo.form.OrderForm;
import com.feng.book_store_demo.service.OrderService;
import com.feng.book_store_demo.vo.OrderDetailVO;
import com.feng.book_store_demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author shf
 * @Date 2020/5/31 22:44
 */
@RestController
@RequestMapping(("/order"))
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid @RequestBody OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getTel());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setSpecsId(orderForm.getSpecsId());
        orderDTO.setBookQuantity(orderForm.getQuantity());

        OrderDTO result = orderService.create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());

        return new ResultVO<>(ResultEnum.SAVE,map);
    }

    @PutMapping("/pay/{orderId}")
    public ResultVO<Map<String,String>> pay(@PathVariable("orderId") String orderId){
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderService.pay(orderId));
        return new ResultVO<>(ResultEnum.UPDATE,map);
    }
    @GetMapping("/detail/{orderId}")
    public ResultVO<OrderDetailVO> getOrderDetail(@PathVariable("orderId") String orderId){
        OrderDetailVO orderDetailVO = orderService.getOrderDetailByOrderId(orderId);
        return new ResultVO<>(ResultEnum.SUCCESS,orderDetailVO);
    }
    @GetMapping("/orderList")
    public ResultVO<List<OrderDetailVO>> orderList(){
        List<OrderDetailVO> detailVOS = orderService.orderList();
        return new ResultVO<>(ResultEnum.SUCCESS,detailVOS);
    }
    @DeleteMapping("/cancel/{orderId}")
    public ResultVO<String> delete(@PathVariable("orderId") String id ){
        orderService.cancel(id);
        return new ResultVO<>(ResultEnum.DELETE,null);
    }

}
