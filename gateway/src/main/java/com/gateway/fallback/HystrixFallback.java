package com.gateway.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixFallback {

    @RequestMapping(value = "/fallback")
    public String sampleFallback() {
        return "Service Down";
    }
}
