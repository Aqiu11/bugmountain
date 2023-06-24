package com.atguigu.eureka;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * ClassName: MyRedisLock
 * Package: com.atguigu.eureka
 * Description:
 *
 * @Author AQiu
 * @Create 07/04/2023 23:07
 */
public class MyRedisLock implements Lock {
    @Override
    public void lock() {
        tryLock();
    }



    @Override
    public boolean tryLock() {
        try {
            ArrayList<Object> objects = new ArrayList<>();
            tryLock(-1,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
    @Override
    public void unlock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }


    @Override
    public Condition newCondition() {
        return null;
    }
}
