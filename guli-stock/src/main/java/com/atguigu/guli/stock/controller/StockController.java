package com.atguigu.guli.stock.controller;

import com.atguigu.guli.stock.bean.Stock;
import com.atguigu.guli.stock.service.StockService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Value("${server.port}") //获取当前服务的端口号
    private Integer port;
    @Resource
    StockService stockService;
    //资源路径：http://localhost:8081/stock/updateStockByPId/1001/2
    //需求： 订单创建成功   传入商品id  扣除订单商品数量
    //根据商品id 修改商品的库存
    @GetMapping("updateStockByPId/{productId}/{count}")
    public Boolean updateStockByPId(@PathVariable("productId")Long productId,
                                    @PathVariable("count")Long count ,
                                    HttpServletRequest request,
                                    @CookieValue(value = "token",required = false)String token) throws InterruptedException {
       // TimeUnit.SECONDS.sleep(6);
        System.out.println("token :" + token);
        System.out.println("库存服务端口号："+port+"被访问了...");
        return stockService.updateStockByPId(productId,count);
    }
}
