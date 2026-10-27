package com.codeit.scopeexample;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // getBean으로 요청할 때마다 새 객체를 만듭니다.
public class PrototypeBean {
    public PrototypeBean() {
        System.out.println("PrototypeBean 생성자 호출");
    }
    // prototype의 초기화는 Spring이 하지만 소멸 콜백은 자동 관리하지 않습니다.
}
