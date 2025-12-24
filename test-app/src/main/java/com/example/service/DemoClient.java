package com.example.service;

import com.example.demo.dubbo.api.DemoService;
import com.example.demo.dubbo.api.Hello;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class DemoClient {
	@DubboReference
	private DemoService demoService;

	public Hello sayHello(String name) {
		return demoService.sayHello(name);
	}
}
