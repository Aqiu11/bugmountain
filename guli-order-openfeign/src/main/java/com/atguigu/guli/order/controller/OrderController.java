package com.atguigu.guli.order.controller;

import com.atguigu.guli.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    //新增订单
    @GetMapping("create/{userId}/{productId}/{count}")
    public String saveOrder(@PathVariable("userId") Long userId,
                             @PathVariable("productId")Long productId,
                             @PathVariable("count")Long count){
        return orderService.saveOrder(userId, productId, count)?"订单创建成功":"订单创建失败";
    }
}
