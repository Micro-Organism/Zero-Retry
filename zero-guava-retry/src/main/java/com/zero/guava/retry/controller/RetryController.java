package com.zero.guava.retry.controller;

import com.zero.guava.retry.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retry")
public class RetryController {

    private final RetryService retryService;

    @Autowired
    public RetryController(RetryService retryService) {
        this.retryService = retryService;
    }

    @GetMapping("/retry")
    public String testRetry() {
        try {
            Boolean result = retryService.executeWithRetry();
            return "操作成功: " + result;
        } catch (Exception e) {
            return "操作失败: " + e.getMessage();
        }
    }

}
