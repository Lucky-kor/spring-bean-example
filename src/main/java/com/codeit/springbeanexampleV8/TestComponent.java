package com.codeit.springbeanexampleV8;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

/** 생성/주입 후 @PostConstruct, 컨테이너 종료 전 @PreDestroy가 호출됩니다. 생성자와 초기화 콜백을 구분하세요. */
@Component
public class TestComponent {

    @PostConstruct
    public void init() {
        System.out.println("TestComponent init method called");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("TestComponent destroy method called");
    }
}
