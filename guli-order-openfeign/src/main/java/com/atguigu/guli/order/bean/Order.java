package com.atguigu.guli.order.bean;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    // auto是使用全局配置策略
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id生成策略: 使用数据库自增长
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "order_sn")
    private String orderSn;//订单编号
    private Long count;
    private BigDecimal money;
    @Column(name = "create_time")
    private Date createTime;
}
