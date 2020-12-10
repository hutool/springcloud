package com.atguigu.cloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// GateWay 网关过滤器
@Component
public class MyGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("*********** 执行过滤器 MyGateWayFilter ***********");
        // 获取请求参数中name的值，http://localhost:9527/service/payment/1?name=1
        String name = exchange.getRequest().getQueryParams().getFirst("name");
        if(name == null){
            System.out.println("************ name不能为空，被过滤器挡在外面 *************");
            return exchange.getResponse().setComplete();
        }

        // 反之放行，执行下一个过滤器
        return chain.filter(exchange);
    }


    // 设置该过滤器的权重，数字越小，权重越大
    @Override
    public int getOrder() {
        return 0;
    }
}
