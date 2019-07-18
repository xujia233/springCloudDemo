package springcloud.eurekafeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springcloud.eurekafeign.api.HelloService;

import javax.annotation.Resource;

// 开启feign的功能
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@RestController
public class EurekaFeignApplication {

    @Resource
    private HelloService helloService;

    public static void main(String[] args) {
        SpringApplication.run(EurekaFeignApplication.class, args);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String sayHello() {
        return helloService.sayHello();
    }

}
