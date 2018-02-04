package com.example.reservationservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestResource {

    @Value("${eureka.instance.metadataMap.instanceId}")
    private String instanceId;

    @GetMapping(path = "test")
    public String testGet() {
        return this.instanceId;
    }

    @PostMapping(path = "test")
    public Map<String, Object> testPost(@RequestBody Map<String, Object> param) {
        return param;
    }
}
