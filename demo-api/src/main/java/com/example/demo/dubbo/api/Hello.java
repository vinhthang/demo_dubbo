package com.example.demo.dubbo.api;

import java.io.Serializable;
import java.time.LocalDateTime;

public record Hello(String name, LocalDateTime time) implements Serializable {
}
