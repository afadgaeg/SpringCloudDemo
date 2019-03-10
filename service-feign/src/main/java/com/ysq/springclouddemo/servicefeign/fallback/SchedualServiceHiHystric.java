package com.ysq.springclouddemo.servicefeign.fallback;

import com.ysq.springclouddemo.servicefeign.callinterface.SchedualServiceHi;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}

