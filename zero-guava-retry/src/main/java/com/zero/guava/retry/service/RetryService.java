package com.zero.guava.retry.service;

public interface RetryService {

    Boolean executeWithRetry() throws Exception;

}
