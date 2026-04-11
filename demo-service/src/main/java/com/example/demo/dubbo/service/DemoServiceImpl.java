package com.example.demo.dubbo.service;

import com.example.demo.dubbo.api.Hello;
import com.example.demo.dubbo.api.HelloService;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@DubboService
public class DemoServiceImpl implements HelloService {

	private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public Hello sayHello(String name) {
		logger.info("Got name :[{}]", name);
		String currentThread =  Thread.currentThread().getName();
        return new Hello("Hello " + name, LocalDateTime.now(), currentThread);
    }
}