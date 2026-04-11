package com.example.controller;

import com.example.demo.dubbo.api.Hello;
import com.example.demo.message.GreeterReply;
import com.example.demo.message.GreeterRequest;
import com.example.service.DemoClient;
import com.example.service.GreeterReplyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/test")
public class TestController {
	private final DemoClient demoClient;

	public TestController(DemoClient demoClient) {
		this.demoClient = demoClient;
	}

	@PostMapping(path = "/greet-future")
	public CompletableFuture<Collection<GreeterReplyDto>> greetStream(@RequestBody GreeterRequest request) {
		return demoClient.greetStream(request);
	}

	@PostMapping(path = "/greet")
	public ResponseEntity<GreeterReply> greet(@RequestBody GreeterRequest request) {
		return ResponseEntity.ok(demoClient.greet(request));
	}

	@GetMapping
	public ResponseEntity<Hello> test(@RequestParam(name = "name") String name) {
		return ResponseEntity.ok(demoClient.sayHello(name));
	}
}
