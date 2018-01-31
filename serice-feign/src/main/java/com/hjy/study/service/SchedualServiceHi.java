package com.hjy.study.service;

import com.hjy.study.service.fallback.SchedualServiceHiHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 定义一个feign接口，通过@ FeignClient（“服务名”），来指定调用哪个服务
 *
 * fallback表示使用断路器，若SchedualServiceHi中的方法有bug，就会执行SchedualServiceHiHystric中的同名方法。
 * Created by hjy on 2018/1/31.
 */
@FeignClient(value = "eurekaclient",fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
