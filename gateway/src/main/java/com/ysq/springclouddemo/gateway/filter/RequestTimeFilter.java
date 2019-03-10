package com.ysq.springclouddemo.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


public class RequestTimeFilter implements GatewayFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(GatewayFilter.class);
    public static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    private Config config;

    public RequestTimeFilter(Config config) {
        this.config = config;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 在处理请求前做一些事，这里先获得请求开始时间
        exchange.getAttributes().put(RequestTimeFilter.REQUEST_TIME_BEGIN, System.currentTimeMillis());
        return chain.filter(exchange).then(
                // 请求处理完毕后做一些事，这里计算执行请求的耗时（当前时间-请求开始时间），并记录到日志
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(RequestTimeFilter.REQUEST_TIME_BEGIN);
                    if (startTime != null) {
                        StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath())
                                .append(": ")
                                .append(System.currentTimeMillis() - startTime)
                                .append("ms");
                        if (config.isWithParams()) { //这里可以获取到传递给filter的参数
                            sb.append(" params:").append(exchange.getRequest().getQueryParams());
                        }
                        logger.info(sb.toString());
                    }
                })
        );

    }

    @Override
    public int getOrder() {
        return 0;
    }

    //这个类协助filter传递参数
    public static class Config {

        private boolean withParams;

        public boolean isWithParams() {
            return withParams;
        }

        public Config setWithParams(boolean withParams) {
            this.withParams = withParams;
            return this;
        }

    }

}


