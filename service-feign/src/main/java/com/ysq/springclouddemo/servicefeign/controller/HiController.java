package com.ysq.springclouddemo.servicefeign.controller;

import com.ysq.springclouddemo.servicefeign.callinterface.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HiController {

    @Autowired
    SchedualServiceHi schedualServiceHi;

    @Value("${foo}")
    String foo;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return schedualServiceHi.sayHiFromClientOne(name) + " foo: " + foo;
    }

}
