package com.hjy.study.controller;

import com.hjy.study.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hjy on 2018/1/31.
 */
@RestController
public class HiController {

    @Autowired
    SchedualServiceHi schedualServiceHi;


    /**
     * 通过Feign客户端SchedualServiceHi 来消费服务
     * @param name
     * @return
     */
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return schedualServiceHi.sayHiFromClientOne(name);
    }
}
