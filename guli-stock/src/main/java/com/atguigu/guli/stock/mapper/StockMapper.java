package com.atguigu.guli.stock.mapper;

import com.atguigu.guli.stock.bean.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface StockMapper extends JpaRepository<Stock , Long> {
    @Transactional //jpa的更新必须加事务
    @Modifying //表示本次操作是更新
    @Query("update Stock s set s.count = s.count - ?2 where s.productId = ?1") //编写自定义sql
    int updateCountByPId(Long productId, Long count);
}
