package com.zero.spring.retry.service;

public interface RemoteApiService {

    boolean pay(int num) throws Exception;

    boolean recover(int num) throws Exception;

}