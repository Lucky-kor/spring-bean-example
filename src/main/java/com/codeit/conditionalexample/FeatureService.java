package com.codeit.conditionalexample;

/** 조건이 만족될 때만 등록되는 일반 Java 객체입니다. @Component를 붙이면 조건을 우회하므로 붙이지 않습니다. */
public class FeatureService {
    public String getMessage() {
        return "Feature X 활성화 됨.";
    }
}
