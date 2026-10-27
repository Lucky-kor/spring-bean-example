package com.codeit.springbeanexampleV2.service;

import org.springframework.stereotype.Service;

/** @Service가 있어도 스캔 필터에 제외되면 등록되지 않습니다. 애너테이션과 스캔 범위를 함께 보세요. */
@Service
public class MyService {
    public void testMethod() {
        System.out.println("testMethod");
    }
}
