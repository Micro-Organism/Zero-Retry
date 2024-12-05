package com.zero.guava.retry;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class ZeroGuavaRetryApplicationTests {

    @Test
    void contextLoads() {
        // 配置重试策略
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                // 定义重试条件：返回 false 或抛出异常时重试
                .retryIfResult(result -> result == null || !result)
                .retryIfException()
                // 设置等待时间策略：每次等待 2 秒
                .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
                // 设置停止条件：最多重试 5 次
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                .build();

        try {
            // 执行需要重试的操作
            Boolean result = retryer.call(() -> performOperation());
            System.out.println("操作成功: " + result);
        } catch (Exception e) {
            System.err.println("操作失败，重试次数已耗尽: " + e.getMessage());
        }

    }

    // 模拟需要重试的操作
    private static Boolean performOperation() {
        System.out.println("执行操作...");
        // 模拟失败逻辑：可以返回 false 或抛出异常来触发重试
        if (Math.random() > 0.5) {
            throw new RuntimeException("随机失败");
        }
        return true; // 模拟成功
    }

}
