package com.zero.spring.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class ZeroSpringRetryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeroSpringRetryApplication.class, args);
    }

}
