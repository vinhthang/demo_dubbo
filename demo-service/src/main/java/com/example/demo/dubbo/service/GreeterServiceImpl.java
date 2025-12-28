package com.example.demo.dubbo.service;

import com.example.demo.message.Greeter;
import com.example.demo.message.GreeterReply;
import com.example.demo.message.GreeterRequest;
import com.google.protobuf.Timestamp;
import org.apache.dubbo.config.annotation.DubboService;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@DubboService
public class GreeterServiceImpl implements Greeter {

	@Override
	public GreeterReply greet(GreeterRequest request) {
		return GreeterReply.newBuilder()
				.setMessage("Hello " + request.getName())
				.setTimestamp(Timestamp.newBuilder()
						.setSeconds(Instant.now().getEpochSecond())
						.setNanos(Instant.now().getNano())
						.build())
				.build();
	}

	@Override
	public CompletableFuture<GreeterReply> greetAsync(GreeterRequest request) {
		return CompletableFuture.completedFuture(greet(request));
	}
}
