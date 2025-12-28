package com.example.demo.dubbo.service;

import com.example.demo.dubbo.api.Hello;
import com.example.demo.dubbo.api.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

import java.time.LocalDateTime;

@DubboService
public class DemoServiceImpl implements HelloService {

    @Override
    public Hello sayHello(String name) {
        return new Hello("Hello " + name, LocalDateTime.now());
    }
}