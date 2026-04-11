package com.example.service;

import com.example.demo.dubbo.api.Hello;
import com.example.demo.dubbo.api.HelloService;
import com.example.demo.message.Greeter;
import com.example.demo.message.GreeterReply;
import com.example.demo.message.GreeterRequest;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboReference;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class DemoClient {

	private final Logger logger = LoggerFactory.getLogger(DemoClient.class.getName());

	@DubboReference
	private HelloService helloService;
	@DubboReference
	private Greeter greaterService;

	public Hello sayHello(String name) {
		return helloService.sayHello(name);
	}

	public CompletableFuture<GreeterReply> greetAsync(GreeterRequest request) {

		return greaterService.greetAsync(request);
	}

	public GreeterReply greet(GreeterRequest request) {

		logger.info("greet request: {}", request);
		GreeterReply reply = greaterService.greet(request);
		logger.info("greet reply: {}", reply);
		return reply;
	}

	public @Nullable CompletableFuture<Collection<GreeterReplyDto>> greetStream(GreeterRequest request) {

		CompletableFuture<Collection<GreeterReplyDto>> future = new CompletableFuture<>();
		List<GreeterReplyDto> result = new ArrayList<>();

		greaterService.greetStream(request, new StreamObserver<>() {
			@Override
			public void onNext(GreeterReply data) {
				result.add(new GreeterReplyDto(data.getMessage()));
			}

			@Override
			public void onError(Throwable throwable) {

			}

			@Override
			public void onCompleted() {

				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				future.complete(result);
			}
		});

		return future;
	}
}
