package com.ysq.springclouddemo.gateway.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeFilter.Config> {


    private static final Logger logger = LoggerFactory.getLogger(RequestTimeGatewayFilterFactory.class);
    private static final String KEY = "withParams";

    @Override
    public List<String> shortcutFieldOrder() {
        //返回Config类的字段列表，估计是为了与下面的 super(Config.class) 结合起来让spring可以通过反射装配Config实例吧。
        return Arrays.asList(KEY);
    }

    public RequestTimeGatewayFilterFactory() {
        super(RequestTimeFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(RequestTimeFilter.Config config) {
        return new RequestTimeFilter(config);
        // 可以用Lambda表达式匿名类，和上面的语句是等价的
//        return (exchange, chain) -> {
//            // 在处理请求前做一些事，这里先获得请求开始时间
//            exchange.getAttributes().put(RequestTimeFilter.REQUEST_TIME_BEGIN, System.currentTimeMillis());
//            return chain.filter(exchange).then(
//                    // 请求处理完毕后做一些事，这里计算执行请求的耗时（当前时间-请求开始时间），并记录到日志
//                    Mono.fromRunnable(() -> {
//                        Long startTime = exchange.getAttribute(RequestTimeFilter.REQUEST_TIME_BEGIN);
//                        if (startTime != null) {
//                            StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath())
//                                    .append(": ")
//                                    .append(System.currentTimeMillis() - startTime)
//                                    .append("ms");
//                            if (config.isWithParams()) { //这里可以获取到传递给filter的参数
//                                sb.append(" params:").append(exchange.getRequest().getQueryParams());
//                            }
//                            logger.info(sb.toString());
//                        }
//                    })
//            );
//        };
    }


}


