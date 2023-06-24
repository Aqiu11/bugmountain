package com.atguigu.guli.order.mapper;

import com.atguigu.guli.order.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMapper extends JpaRepository<Order , Long> {
}
