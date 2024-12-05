package com.zero.spring.retry.service.impl;

import com.zero.spring.retry.service.RemoteApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class RemoteApiServiceImpl implements RemoteApiService {

    @Override
    @Retryable(retryFor = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public boolean pay(int num) throws Exception {
        log.info("invoke third method");
        log.info("do something... {}", LocalDateTime.now());
        //mock exception
        if (num == 0) {
            throw new Exception("error，need retry");
        }
        return true;
    }

    @Override
    @Recover
    public boolean recover(int num) {
        log.info("recover ... {},{}", num, LocalDateTime.now());
        return false;
    }

}