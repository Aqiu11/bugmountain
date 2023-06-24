package com.atguigu.guli.order.mapper;

import com.atguigu.guli.order.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName: orderMapper
 * Package: com.atguigu.guli.order.mapper
 * Description:
 *
 * @Author zdhstart
 * @Create 2023/3/16 11:10
 * @Version 1.0
 */
public interface OrderMapper extends JpaRepository<Order ,Long> {
}
