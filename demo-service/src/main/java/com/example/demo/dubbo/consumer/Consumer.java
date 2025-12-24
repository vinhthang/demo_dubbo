package com.example.demo.dubbo.consumer;

import com.example.demo.dubbo.api.DemoService;

import com.example.demo.dubbo.api.Hello;
import org.apache.dubbo.config.annotation.DubboReference;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Consumer implements CommandLineRunner {
    @DubboReference
    private DemoService demoService;

    @Override
    public void run(String... args) throws Exception {

        Hello result = demoService.sayHello("world");
        System.out.println("Receive result ======> " + result);
    }
}
