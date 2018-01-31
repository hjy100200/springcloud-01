package com.hjy.study.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 通过之前注入ioc容器的restTemplate来消费【eurekaclient】服务的“/hi”接口
 * Created by hjy on 2018/1/30.
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 直接用的程序名替代了具体的url地址，在ribbon中它会根据服务名来选择具体的服务实例，根据服务实例在请求的时候会用具体的url替换掉服务名。
     * 名称必须使用eurekaclient服务的application.properties中的spring.application.name一样（大小写也必须一样）
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "hiError")//对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法.如果该方法执行有问题，就会执行hiError方法。
    public String hiService(String name) {
        return restTemplate.getForObject("http://eurekaclient/hi?name="+name,String.class);
    }

    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }

}
