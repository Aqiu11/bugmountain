package com.atguigu.guli.order.controller;

import com.atguigu.guli.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: OrderController
 * Package: com.atguigu.guli.order.controller
 * Description:
 *
 * @Author zdhstart
 * @Create 2023/3/16 11:18
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    //新增订单
    @GetMapping("create/{userId}/{productId}/{count}")
    public String createOrder(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId, @PathVariable("count") Long count){

        return orderService.create(userId,productId,count)?"成功":"失败";

    }
}
