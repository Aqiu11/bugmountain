package com.atguigu.guli.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 远程访问一个接口
// 1、服务器地址+端口号： 可以通过注册中心以服务名拉取
@FeignClient(value = "GULI-STOCK") //GULI-STOCK ==》服务器地址+端口号  http://localhost:8081
public interface StockClient {
    //2、资源路径： http://localhost:8081/stock/updateStockByPId/{productId}/{count}    GetMapping代表请求方式
    //3、参数列表： 通过方法的形参列表携带(@PathVariable 代表路径参数  @RequestParam代表请求参数)
    @GetMapping("/stock/updateStockByPId/{productId}/{count}")
    public Boolean updateStockByPId(@PathVariable("productId")Long productId,
                                    @PathVariable("count")Long count );//方法返回值类型代表响应结果要封装的类型
}
