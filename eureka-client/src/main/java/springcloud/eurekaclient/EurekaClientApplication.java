package springcloud.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// 表明自己是一个client
@EnableEurekaClient
@SpringBootApplication
@RestController
public class EurekaClientApplication {

    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "hi，菜虚坤，i'm from：" + port;
    }
}
