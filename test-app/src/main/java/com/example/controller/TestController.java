package com.example.controller;

import com.example.demo.dubbo.api.Hello;
import com.example.service.DemoClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	private final DemoClient demoClient;

	public TestController(DemoClient demoClient) {
		this.demoClient = demoClient;
	}

	@GetMapping
	public ResponseEntity<Hello> test(@RequestParam(name = "name") String name) {
		return ResponseEntity.ok(demoClient.sayHello(name));
	}
}
