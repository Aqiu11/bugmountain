package com.atguigu.guli.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/*
    全局过滤器：
           请求访问网关时，如果匹配到了路由 此时全局过滤器生效，否则不生效
    获取请求路径和请求头host输出
 */
@Component
public class GuliGlobalFilter implements  GlobalFilter, Ordered {
    //过滤方法
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //请求对象
        ServerHttpRequest request = exchange.getRequest();
        //如果请求路径包含/order放行请求，否则拒绝请求
        String path = request.getURI().getPath();
        // ?代表一个字符  *代表一层级任意多个字符  **代表任意多层级多个字符
        AntPathMatcher matcher = new AntPathMatcher();
        boolean flag = matcher.match("/order/**", path);
        System.out.println("客户端请求的路径："+ path);
        System.out.println("请求头host："+ request.getHeaders().getFirst("Host"));
        if(flag){
            //放行请求到目标微服务
            return chain.filter(exchange);
        }
        //不包含给前端一个错误响应
        //响应对象
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        return response.setComplete();

    }



    //当前过滤器执行的优先级：所有的全局过滤器 优先级高的先执行
    // 值越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
