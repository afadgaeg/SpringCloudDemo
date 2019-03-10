# SpringCloudDemo
一个包括Eureka、Spring Cloud Config、Feign、Spring Cloud Gateway、Hystrix、Hystrix Dashboard、Hystrix Turbine插件的Spring Cloud简单Demo

## 模块说明

包括6个模块：

eureka-server、gateway、config-server、service-hi、service-feign、turbine-server

服务实现高可用

未使用组件和功能：
- 消息总线Spring Cloud Bus
- 服务链路追踪Spring Cloud Sleuth/zipkin
- 网关限流RequestRateLimiter

### eureka-server

服务中心

### gateway

微服务网关，注册到服务中心。使用了Spring Cloud拆箱可用的部分断言、过滤器，
使用自定义全局过滤器TokenFilter检查请求header中是否包括token
自定义网关过滤器RequestTimeGatewayFilter和网关过滤器工厂RequestTimeGatewayFilterFactory来实现日志记录请求耗时
使用Hystrix断路保护和定义了fallback

### config-server

提供github分布式配置中心

### service-hi

简单的微服务提供者

### service-feign

通过feign实现服务消费，实现断路保护。
未演示rest+ribbon

### turbine-server

断路器聚合监控，用来监控service-hi、service-feign的断路器状况
