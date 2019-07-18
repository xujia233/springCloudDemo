package springcloud.eurekafeign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springcloud.eurekafeign.impl.HelloServiceFallback;

/**
 * Feign有点类似于retrofit，是一种声明式的web服务客户端，支持可拔插的注解，包含Feign注解和JAX-RS注解，Srping Cloud增加了对Spring MVC注解的集成
 * Created by xujia on 2019/7/17
 */
@FeignClient(value = "SERVICE-HELLO", fallback = HelloServiceFallback.class)
public interface HelloService {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    String sayHello();
}
