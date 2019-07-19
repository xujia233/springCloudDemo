package springcloud.eurekaribbon.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xujia on 2019/7/17
 */
@Service
public class HelloServiceImpl {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @HystrixCommand(fallbackMethod = "sayHelloFallback",
            // 接下来均为Hystrix性能配置，更多配置参考官方文档
            commandProperties = {
            // 执行超时时间|default:1000
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),

            // 下面的为度量策略，即触发熔断的配置，这里为了测试均采用很极端的配置，实际开发中，这部分参数调优是很麻烦的...
            // 触发断路最低请求数|default:20
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            // 统计周期内度量桶的数量，窗口拆分数|默认10
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "1"),
            // 5s为一次统计周期，专业术语为：窗口维持时间|默认10000
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
            // 触发断路的时间值，即当触发熔断后的10s内均会快速失败，不会发起请求，当10s后才会关闭断路器，默认为5s
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")},

            // 下面是线程池参数
            threadPoolProperties = {
            // 线程池核心数|default:10
            @HystrixProperty(name = "coreSize", value = "10"),
            // 队列长度|default:-1(SynchronousQueue)，值为正数时使用LinkedBlockQueue
            @HystrixProperty(name = "maxQueueSize", value = "20"),
            // 队满拒绝服务阈值|default:5|此值生效优先于队满
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
             })
    public String sayHello() {
        // 用来验证有去调用服务提供者
        logger.info("Try to consume ...");
        // 使用restTemplate基于http的restful方式在服务之间通信
        // 方法前缀get即表示get请求，第一个参数即为url，这里直接使用对应服务名替代，返回的Object类型会根据第二个参数进行自动转换
        return restTemplate.getForObject("http://SERVICE-HELLO/hello", String.class);
    }

    public String sayHelloFallback() {
        return "hi，菜虚坤，sorry，服务不可用";
    }
}
