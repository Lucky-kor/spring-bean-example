package com.codeit.dependexample.dependson;

import org.springframework.stereotype.Component;

/** FirstBean이 초기화 순서에 의존하는 대상입니다. 파일 이름/스캔 순서로 순서를 보장하지 마세요. */
@Component
public class SecondBean {
    public SecondBean() {
        System.out.println("SecondBean constructor called");
    }
}
