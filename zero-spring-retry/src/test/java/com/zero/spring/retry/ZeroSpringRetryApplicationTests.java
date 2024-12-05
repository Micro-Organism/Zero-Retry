package com.zero.spring.retry;

import com.zero.spring.retry.service.RemoteApiService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ZeroSpringRetryApplicationTests {

    @Autowired
    RemoteApiService remoteApiService;

    @BeforeEach
    public void before()  {
        log.info("init some data");
    }

    @AfterEach
    public void after(){
        log.info("clean some data");
    }

    @Test
    public void execute() throws Exception {
        log.info("pay result:{}", remoteApiService.pay(0));
    }

}
