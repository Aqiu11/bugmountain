package com.atguigu.guli.order.service;

import com.atguigu.guli.order.bean.Order;
import com.atguigu.guli.order.mapper.OrderMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * ClassName: OederService
 * Package: com.atguigu.guli.order.service
 * Description:
 *
 * @Author zdhstart
 * @Create 2023/3/16 11:12
 * @Version 1.0
 */
@Service
public class OrderService {
    @Resource
    OrderMapper orderMapper;
    @Autowired
    RestTemplate restTemplate;

    public Boolean create(Long userId,Long productId,Long count){
        Order order = new Order();
        order.setProductId(productId);
        order.setCount(count);
        order.setOrderSn(UUID.randomUUID().toString().replace("-",""));
        order.setMoney(new BigDecimal(count*100));
        order.setUserId(userId);
        order.setCreateTime(new Date());
        Boolean flag = orderMapper.save(order) !=null;

        //TODO: 更新库存
        //远程访问库存服务减库存的接口
        //参数1：目标接口地址  参数2：目标接口返回的响应体数据要封装的类型  参数3：可变参数列表 填充参数1中的占位符
        System.out.println(restTemplate.getForObject("http://GULI-STOCK/stock/updateStockByPId/{1}/{2}", String.class,
                productId, count));

        return flag;
    }
}

        /*try {
            HttpClient httpClient = new DefaultHttpClient();
            //String path = "http://localhost:8081/stock/updateStockByPId/"+productId+"/"+count";
            String path = "http://localhost:8081/stock/updateStockByPId/%s/%s";
            path = String.format(path, productId,count);
            HttpGet requst = new HttpGet(path);
            HttpResponse response = httpClient.execute(requst);
            String s = EntityUtils.toString(response.getEntity());
            flag = Boolean.parseBoolean(s);
            System.out.println("扣除库存"+s);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
