package com.atguigu.guli.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: RibbonConfig
 * Package: com.atguigu.guli.order.config
 * Description:
 *
 * @Author zdhstart
 * @Create 2023/3/16 14:41
 * @Version 1.0
 */
@Configuration
public class RibbonConfig {

    //RestTemplate是springweb包提供的 对httpClient封装后的 网络请求的工具类
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
