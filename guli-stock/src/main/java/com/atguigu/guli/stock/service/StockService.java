package com.atguigu.guli.stock.service;

import com.atguigu.guli.stock.mapper.StockMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Transactional
public class StockService {


    @Resource
    StockMapper  stockMapper;

    public Boolean updateStockByPId(Long productId, Long count) {
        // 如果stock对象没有id 则保存  如果有则更新
        int i = stockMapper.updateCountByPId(productId,count);
        ReentrantLock lock = new ReentrantLock();
        lock.unlock();
        return i==1;//如果更新成功的记录为1条代表更新成功
    }



}




