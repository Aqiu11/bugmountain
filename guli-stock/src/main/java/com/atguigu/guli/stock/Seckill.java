package com.atguigu.guli.stock;

import java.util.Random;

/**
 * ClassName: killsecond
 * Package: com.atguigu.guli.stock
 * Description:
 *
 * @Author AQiu
 * @Create 23/04/2023 15:11
 */

    public  class Seckill {
        private static  int ITEM_COUNT = 10; // 商品数量

        public static void main(String[] args) {
            // 创建顾客线程
            for (int i = 0; i < 100; i++) {
                new Thread(() -> {
                    // 随机睡眠0-2秒,模拟不同网速的用户
                    Random rnd = new Random();
                    try {
                        Thread.sleep(rnd.nextInt(2000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 进行秒杀操作
                    seckill();
                }).start();
            }
        }

        // 秒杀操作
        private static void seckill() {
            // 判断是否还有商品
            if (ITEM_COUNT <= 0) {
                System.out.println("商品已售空!");
                return;
            }

            // 假设睡眠时间够长,可以秒杀成功
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 秒杀成功
            System.out.println(Thread.currentThread().getName() + "秒杀成功!");
            ITEM_COUNT--;
        }
    }

