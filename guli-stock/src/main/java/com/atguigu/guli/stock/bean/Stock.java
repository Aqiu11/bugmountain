package com.atguigu.guli.stock.bean;

import lombok.Data;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
@Entity
@Table(name = "t_stock")
public  class Stock {
    @Id
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    private String title;
    private Long count;

    public static  void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        System.out.println();

    }
}
