package com.hjy.study.service.fallback;

import com.hjy.study.service.SchedualServiceHi;
import org.springframework.stereotype.Component;

/**
 * Created by hjy on 2018/1/31.
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}