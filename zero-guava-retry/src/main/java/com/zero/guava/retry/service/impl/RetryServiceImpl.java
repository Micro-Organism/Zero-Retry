package com.zero.guava.retry.service.impl;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.zero.guava.retry.service.RetryService;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Service
public class RetryServiceImpl implements RetryService {

    private final Retryer<Boolean> retryer;

    public RetryServiceImpl() {
        // 配置重试策略
        this.retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(result -> result == null || !result)
                .retryIfException()
                .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();
    }

    @Override
    public Boolean executeWithRetry() throws Exception {

        // 调用任务并执行重试
        return retryer.call(this.retryTask());
        // 使用方法函数
//        return retryer.call(this::performOperation);

    }

//    private final Callable<Boolean> retryTask = this::performOperation;
    private Callable<Boolean> retryTask() {
        // 定义需要重试的任务
        // 模拟失败逻辑（抛出异常或返回 false）
        return () -> {
            System.out.println("尝试执行任务...");
            // 模拟失败逻辑（抛出异常或返回 false）
            if (Math.random() > 0.7) {
                throw new RuntimeException("模拟异常");
            }
            return true;
        };
    }

    private Boolean performOperation() {
        System.out.println("执行操作...");
        if (Math.random() > 0.7) {
            throw new RuntimeException("模拟异常");
        }
        return true;
    }
}
