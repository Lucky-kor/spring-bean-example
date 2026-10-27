package com.codeit.springbeanexampleV8;

/** 일반 Java 초기화/정리 메서드입니다. AppConfig가 이름을 지정하여 컨테이너가 호출합니다. */
public class TestBean {
    private void cleanup() {
        System.out.println("TestBean cleanup method called");
    }

    private void init() {
        System.out.println("TestBean init method called");
    }
}
