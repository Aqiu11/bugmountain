package com.atguigu.guli.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

// 如果filter工厂类命名为XxxGatewayFilterFactory Xxx就是工厂类生产的filter的名称
@Component
public class GuliAddTokenGatewayFilterFactory extends AbstractGatewayFilterFactory {


    //以后配置当前工厂类的路由 会调用此方法得到一个过滤器对象 作用到配置的路由上
    @Override
    public GatewayFilter apply(Object config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                /*
                    在请求头中添加token=UUID的值  透传给后续的服务使用
                 */
                ServerHttpRequest request = exchange.getRequest();
                //修改request和response时必须调用mutate方法修改
                request.mutate().header("cookie","token="+
                        UUID.randomUUID().toString().replace("-",""))
                        .build();
                //将修改后的request设置到交换机中构建新的exchange对象
                exchange.mutate().request(request).build();
                //放行
                return chain.filter(exchange);
            }
        };
    }
    //手动配置局部过滤器的name
    @Override
    public String name() {
        return "addToken";
    }
}
