package springcloud.eurekafeign.impl;

import org.springframework.stereotype.Service;
import springcloud.eurekafeign.api.HelloService;

/**
 * 当前类需要被注入到IOC容器
 * Created by xujia on 2019/7/17
 */
@Service
public class HelloServiceFallback implements HelloService {
    @Override
    public String sayHello() {
        return "hi，菜虚坤，sorry，服务不可用";
    }
}
