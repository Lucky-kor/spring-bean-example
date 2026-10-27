package com.codeit.springbeanexample.service;

import org.springframework.stereotype.Service;

/** @Service는 컴포넌트 스캔 대상입니다. 기본 빈 이름은 myService이며 기본 스코프는 singleton입니다. */
@Service
public class MyService {
    public void testMethod() {
        System.out.println("testMethod");
    }
}
