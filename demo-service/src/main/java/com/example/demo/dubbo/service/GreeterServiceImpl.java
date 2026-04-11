package com.example.demo.dubbo.service;

import com.example.demo.message.DubboGreeterTriple;
import com.example.demo.message.Greeter;
import com.example.demo.message.GreeterReply;
import com.example.demo.message.GreeterRequest;
import com.google.protobuf.Timestamp;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@DubboService(serialization = CommonConstants.GENERIC_SERIALIZATION_PROTOBUF, interfaceClass = Greeter.class, protocol = CommonConstants.TRIPLE)
public class GreeterServiceImpl extends DubboGreeterTriple.GreeterImplBase {

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

	@Override
	public void greetStream(GreeterRequest request, StreamObserver<GreeterReply> responseObserver) {
		for (int i = 0; i < 10; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			responseObserver.onNext(greet(request));
		}
		responseObserver.onCompleted();
	}
}
