package com.atguigu.guli.order.service;

import com.atguigu.guli.order.bean.Order;
import com.atguigu.guli.order.feign.StockClient;
import com.atguigu.guli.order.mapper.OrderMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {
    @Resource
    OrderMapper orderMapper;
    @Resource
    StockClient stockClient;
    //新增订单
    @HystrixCommand(fallbackMethod = "saveOrderFallback")//配置当前方法出现异常时的兜底方案
    public Boolean saveOrder(Long userId,Long productId,Long count){
        //根据传入的参数创建order对象保存到数据库
        Order order = new Order();
        order.setCount(count);
        order.setCreateTime(new Date());
        order.setOrderSn(UUID.randomUUID().toString().replace("-",""));
        order.setProductId(productId);
        order.setUserId(userId);
        order.setMoney(new BigDecimal(1000*count));
        boolean flag = orderMapper.save(order) != null;
        //TODO: 更新库存
        //远程访问库存服务减库存的接口
        //原理：就是网络请求  分布式架构将网络请求的服务配置在注册中心管理
        flag = stockClient.updateStockByPId(productId, count);
        return flag;
    }
    //saveOrder的兜底方法：如果saveOrder执行没有异常兜底方法不会执行，一旦saveOrder执行出错该方法会被调用返回结果
    public Boolean saveOrderFallback(Long userId,Long productId,Long count){
        //根据传入的参数创建order对象保存到数据库
        log.error("保存订单失败，进入熔断降级方法,参数列表：userId={}," +
                "productId={},count={}" , userId,productId,count);
        return false;
    }
}
//        try {
//            HttpClient httpClient = new DefaultHttpClient();
//            //
//            String path = "http://localhost:8081/stock/updateStockByPId/"+productId+"/"+count;
//           // String path = "http://localhost:8081/stock/updateStockByPId/%s/%s";
//           // path = String.format(path ,productId , count );
//            HttpGet request = new HttpGet(path);
//            HttpResponse response = httpClient.execute(request);
//            String s = EntityUtils.toString(response.getEntity());
//            flag = Boolean.parseBoolean(s);
//            System.out.println("扣除库存结果："+s);
//        } catch (Exception e) {
//            e.printStackTrace();
//            flag = false;
//        }
/*
    分布式架构：
        1、通过springboot将项目拆分成多个微服务开发
        2、有些请求需要多个服务协作处理
            创建订单 保存订单后需要更新库存
            订单服务需要远程访问库存服务
            方式： 基于网络编程 发起网络请求直接访问
            httpClient的缺陷：
                    1、地址写死了
                            环境切换时需要修改代码中的地址
                    2、远程访问的结果处理
                            测试中只考虑成功，以后实际业务中会出现各种各样的错误
                            如果远程访问的服务处理速度过慢 会导致雪崩
                    3、如果目标服务多实例启动 需要配置负载均衡
           为了解决远程访问的问题，诞生了众多分布式架构框架 分别解决不同的问题
           springcloud基于市面上已有的常用的分布式框架进行了封装
           springcloud-starter-xxxxx

           注册中心：管理注册到自己的服务的 名称映射ip列表的数据
                eureka：



 */
