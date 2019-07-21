package springcloud.eurekaribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springcloud.eurekaribbon.impl.HelloServiceImpl;

// 由于使用的是Eureka注册中心，因此使用如下该注解注册到注册中心即可，当然也可使用：@EnableDiscoveryClient
@EnableEurekaClient
@EnableCircuitBreaker // 开启断路器功能
@SpringBootApplication
@RestController
public class EurekaRibbonApplication {

    @Autowired
    private HelloServiceImpl helloService;

    public static void main(String[] args) {
        SpringApplication.run(EurekaRibbonApplication.class, args);
    }

    @Bean
    @LoadBalanced // 开启负载均衡功能
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test() {
        return helloService.sayHello();
    }
}
