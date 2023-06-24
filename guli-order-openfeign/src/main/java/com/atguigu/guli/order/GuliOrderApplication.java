package com.atguigu.guli.order;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication //默认扫描自己所在包和子包下的 spring组件 controller service repository component configuration
// @MapperScan 可以扫描mapper接口创建对象添加到容器中
@EnableFeignClients //扫描自己所在包和子包下的所有加了@FeignClient注解的接口创建对象添加到容器中
@EnableDiscoveryClient
@EnableCircuitBreaker //启动断路器  必须加
@EnableHystrixDashboard //启用断路器监控面板
public class GuliOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuliOrderApplication.class, args);
    }
    //springboot默认输出日志的级别为：info
    // debug  info warn error
    @Bean
    public Logger.Level level(){
        //Full表示输出远程访问的所有日志   feign日志输出级别为debug
        return Logger.Level.FULL;
    }
    // springboot项目默认所有的请求都有springgmvc的前端控制器来处理
    //配置监控数据流获取数据流视图的servlet
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
