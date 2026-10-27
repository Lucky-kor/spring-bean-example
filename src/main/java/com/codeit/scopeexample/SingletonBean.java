package com.codeit.scopeexample;

import org.springframework.stereotype.Component;

@Component // 별도 @Scope가 없으면 기본 singleton입니다.
public class SingletonBean {
    public SingletonBean() {
        System.out.println("SingletonBean 생성자 호출");
    }
}
